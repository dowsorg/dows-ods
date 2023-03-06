package org.dows.ods.biz.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.bo.FieldConfig;
import org.dows.ods.bo.JsonConfig;
import org.dows.ods.bo.TableConfig;
import org.dows.ods.bo.ValueConfig;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 接口返回结果JSON的读取工具
 */
public class JsonHelper {

    /**
     * 将输入字符串转换为JsonParse对象
     * */
    public static JsonConfig parse$ValidateJsonConfig(String jsonStr){
        JsonConfig jsonConfig = JSONUtil.toBean(jsonStr, JsonConfig.class);
        if(Objects.isNull(jsonConfig)){
            throw new BizException("解析规则处理失败");
        }
        // 调用验证功能
        validateJsonConfig(jsonConfig);
        return jsonConfig;
    }

    /**
     * json规则的验证函数
     * */
    public static void validateJsonConfig(JsonConfig jsonConfig)throws BizException{
        if(!StringUtils.hasText(jsonConfig.getJsonPath())){
           throw new BizException("json中取结果的jsonPath必须填写");
        }
        List<TableConfig> tableConfigs = jsonConfig.getTableConfigs();
        if(CollectionUtils.isEmpty(tableConfigs)){
            throw new BizException("json解析结果的对应的数据表规则必须填写");
        }
        for (TableConfig tableConfig : tableConfigs) {
            TableHelper.validate(tableConfig);
        }
    }

    /**
     * 将接口请求结果json字符串解析为Map<combineId,<field,value>> 类型的map
     * */
    public static Map<String, Map<String, Object>> parseJson2TableFieldValueMap(String jsonStr, JsonConfig jsonConfig) {
        // <结果集定义> key:table_组合id
        Map<String, Map<String, Object>> tableCombineIdFieldValueMap = new HashMap<>();
        JSONArray records = JSONUtil.getByPath(JSONUtil.parse(jsonStr), jsonConfig.getJsonPath(), new JSONArray());
        List<TableConfig> tableConfigRules = jsonConfig.getTableConfigs();
        // 一个接口的返回结果可能需要多个表的解析规则
        for (int i = 0; i < records.size(); i++) {
            JSONObject jsonData = records.getJSONObject(i);
            // 该条记录根据N张表的解析配置
            for (int j = 0; j < tableConfigRules.size(); j++) {
                TableConfig tableConfigRule = tableConfigRules.get(j);
                String tableName = tableConfigRule.getTableName();
                // 字段配置
                List<FieldConfig> fieldConfigList = tableConfigRule.getFieldConfigs();
                // key 为json源字段的map,进行分区：区分符合条件的和不符合条件的
                Map<Boolean, List<FieldConfig>> partitionByJsonFieldName = fieldConfigList.stream()
                    .collect(Collectors.partitioningBy(f -> StringUtils.hasText(f.getJsonFieldName())));
                List<FieldConfig> fieldsHasJsonFieldConfigName = partitionByJsonFieldName.get(true);
                Map<String, FieldConfig> fieldMapHasJsonFieldName = fieldsHasJsonFieldConfigName.stream()
                    .collect(Collectors.toMap(FieldConfig::getJsonFieldName,
                        Function.identity()));
                if (Objects.isNull(fieldConfigList) || fieldConfigList.isEmpty()) {
                    // 没有配置field规则或者字段规则为空
                    // 跳过,不处理该条字段但也不影响其他字段的解析
                    continue;
                }
                // 处理每条数据记录
                Map<String, Object> targetFieldValueMap = new LinkedHashMap<>();
                // 收集为主键的字段值
                LinkedHashSet<Object> primaryFieldValueSet = new LinkedHashSet<>();
                for (Map.Entry<String, Object> entry : jsonData.entrySet()) {
                    // 根据源数据中的字段名获得即将存入的目标字段名
                    FieldConfig fieldConfig = fieldMapHasJsonFieldName.get(entry.getKey());
                    if (Objects.isNull(fieldConfig)) {
                        // 结果集字段对应的没有设置对应解析字段，直接跳过
                        continue;
                    }
                    String targetFieldName = fieldConfig.getTableFieldName();
                    if (StringUtils.hasText(targetFieldName)) {
                        // 如果该源字段名在字段解析规则中存在，就需要新字段名和对应字段值的加入
                        // 获取实际字段的值，并保存进每张数据表的结果集中
                        Object jsonValue = entry.getValue();
                        // 最终字段的值
                        Object targetValue = Objects.isNull(jsonValue) ? fieldConfig.getDefaultValue() : jsonValue;
                        List<ValueConfig> valueConfigMappings = fieldConfig.getValueConfigs();
                        if (Objects.nonNull(jsonValue) && CollUtil.isNotEmpty(valueConfigMappings)) {
                            // 接口请求结果中字段的值可能有更细的规则匹配
                            Map<Object, ValueConfig> fieldValueMap = valueConfigMappings.stream()
                                .collect(Collectors.toMap(ValueConfig::getJsonFieldValue, Function.identity()));
                            ValueConfig valueConfig = fieldValueMap.get(jsonValue);
                            if (Objects.nonNull(valueConfig)) {
                                targetValue = valueConfig.getTableFieldValue();
                            }
                        }
                        // 如果当前是主键列值的话也要收集一下之后做组合ID表中数据去重使用
                        if (BooleanUtils.isTrue(fieldConfig.getPrimaryKeyTag())) {
                            primaryFieldValueSet.add(targetValue);
                        }
                        // 如果当前的字段值需要加密
                        if(BooleanUtils.isTrue(fieldConfig.getEncryptValueTag())){
                            targetValue = CryptoHelper.cryptoValue(fieldConfig.getCryptoConfig().getCryptoType(), targetValue);
                        }
                        // 解析好的字段名和值
                        targetFieldValueMap.put(targetFieldName, targetValue);
                    }
                }
                // 再放入原json数据中没有映射但是数据表有需要的功能字段
                List<FieldConfig> fieldsWithoutJsonFieldConfigName = partitionByJsonFieldName.get(false);
                for (FieldConfig fieldConfig : fieldsWithoutJsonFieldConfigName) {
                    Object defaultValue = fieldConfig.getDefaultValue();
                    // 如果当前的字段值需要加密
                    if(BooleanUtils.isTrue(fieldConfig.getEncryptValueTag())){
                        defaultValue = CryptoHelper.cryptoValue(fieldConfig.getCryptoConfig().getCryptoType(), defaultValue);
                    }
                    targetFieldValueMap.put(fieldConfig.getTableFieldName(), defaultValue);
                }
                // 组合ID，形式类似 "tableName_primaryKey1_primaryKey2_..."
                String combineTable$PrimaryFieldValueId = CombineIdUtil.generateCombineId(tableName, primaryFieldValueSet.toArray(new String[0]));
                Map<String, Object> fieldValueMap = tableCombineIdFieldValueMap.computeIfAbsent(combineTable$PrimaryFieldValueId, s -> new LinkedHashMap<>());
                // 重叠的字段对应值覆盖，新的字段就新增
                fieldValueMap.putAll(targetFieldValueMap);
            }
        }
        return tableCombineIdFieldValueMap;
    }
}
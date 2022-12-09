package org.dows.ods.biz.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 接口返回结果JSON的读取工具
 */
public class JsonParseUtil {
    /*分隔 jsonPath 和 表的解析规则的分隔符*/
    private static final String JSONPATH$RULE_REGEX = "\\|";
    /*组合Id的拼接和解析的分隔符*/
    private static final String COMBINE_ID_DELIMITER="+";

    public static Map<String, Map<String, Object>> parseJson2TableFieldValueMap(String jsonStr, String jsonPath$Rule) {
        // <结果集定义> key:table_组合id
        Map<String, Map<String, Object>> tableCombineIdFieldValueMap = new HashMap<>();
        String[] splitStrArray = jsonPath$Rule.split(JSONPATH$RULE_REGEX);
        // 使用 "|" 分割后第0位是获取要解析的结果集的jsonPath，第一位以及之后就是解析结果对应数据表的解析规则
        JSONArray records = JSONUtil.getByPath(JSONUtil.parse(jsonStr), splitStrArray[0], new JSONArray());
        JSONArray rules = JSONUtil.parseArray(splitStrArray[1]);
        // 一个接口的返回结果可能需要多个表的解析规则
        for (int i = 0; i < records.size(); i++) {
            JSONObject sourceData = records.getJSONObject(i);
            // 该条记录根据N张表的解析配置
            for (int j = 0; j < rules.size(); j++) {
                JSONObject tableRule = rules.getJSONObject(j);
                String tableName = tableRule.getStr(TableConf.TABLE.getName());
                // 字段配置
                JSONObject fieldConf = tableRule.getJSONObject(TableConf.FIELD.getName());
                // 结果中表可以配置的唯一主键
                Set<String> primaryKeySet = tableRule.getJSONArray(TableConf.PRIMARY_KEY.getName()).toList(String.class)
                    .stream().collect(Collectors.toSet());
                if (Objects.isNull(fieldConf) || fieldConf.isEmpty()) {
                    // 没有配置field规则或者字段规则为空
                    // 报错还是跳过，最终选择了跳过
                    continue;
                }
                // 处理每条数据记录
                Map<String, Object> targetFieldValueMap = new HashMap<>();
                for (Map.Entry<String, Object> entry : sourceData.entrySet()) {
                    // 根据源数据中的字段名获得即将存入的目标字段名
                    String targetFieldName = fieldConf.getStr(entry.getKey());
                    if (StringUtils.hasText(targetFieldName)) {
                        // 如果该源字段名在字段解析规则中存在，就需要新字段名和对应字段值的加入
                        // 获取实际字段的值，并保存进每张数据表的结果集中
                        Object value = entry.getValue();
                        targetFieldValueMap.put(targetFieldName, value);
                    }
                }

                // 组合ID，形式类似 "tableName_primaryKey1_primaryKey2_..."
                StringJoiner combineIdBuilder = new StringJoiner(COMBINE_ID_DELIMITER);
                combineIdBuilder.add(tableName);
                for (String primaryKey : primaryKeySet) {
                    // 将表的primaryKey 对应的字段值获取
                    combineIdBuilder.add(String.valueOf(targetFieldValueMap.get(primaryKey)));
                }
                Map<String, Object> fieldValueMap = tableCombineIdFieldValueMap
                    .computeIfAbsent(combineIdBuilder.toString(), s -> new HashMap<>());
                // 重叠的字段对应值覆盖，新的字段就新增
                fieldValueMap.putAll(targetFieldValueMap);
            }
        }
        return tableCombineIdFieldValueMap;
    }

    public static String getTableNameFromCombineId(String combineId){
        if(StringUtils.hasText(combineId)){
            // 截取到第一个分隔符为止的字符串为表名
            return combineId.substring(0,combineId.indexOf(COMBINE_ID_DELIMITER));
        }
        throw new IllegalArgumentException("组合ID没有字符内容");
    }

    public static String getJsonpath$ruleRegex() {
        return JSONPATH$RULE_REGEX;
    }

    public static String getCombineIdDelimiter() {
        return COMBINE_ID_DELIMITER;
    }
}

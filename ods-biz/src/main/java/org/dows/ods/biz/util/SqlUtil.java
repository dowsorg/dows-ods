package org.dows.ods.biz.util;

import org.springframework.util.StringUtils;

import java.util.*;

public class SqlUtil {
    /**
     * 占位符
     */
    private static final String PLACE_HOLDER = "?";

    //根据Map<String, Map<String, Object>> 生成插入数据的sql语句
    // key:table+primary_key,value:insert tableName()
    public static Map<String, List<List<Object>>> generateSqlMapByTableFieldValueMap(
        Map<String, Map<String, Object>> tableFieldValueMap) {
        Map<String, List<List<Object>>> resMap = new HashMap<>();
        // 以sql语句为键,value:List<List<Object>>
        for (Map.Entry<String, Map<String, Object>> entry : tableFieldValueMap.entrySet()) {
            // 将组合ID拆卸出表名 当前遍历的是一条记录
            String tableName = CombineIdUtil.getTableNameFromCombineId(entry.getKey());
            // 开始解析字段值
            Map<String, Object> record = entry.getValue();
            // 生成一条sql
            StringBuilder sqlBuilder = new StringBuilder(" insert into ");
            sqlBuilder.append(tableName);
            StringJoiner insertSqlFields = new StringJoiner(",", "(", ")");
            StringJoiner insertSqlValues = new StringJoiner(",", "values(", ")");
            LinkedList<Object> sqlParam = new LinkedList<>();
            for (Map.Entry<String, Object> fieldValue : record.entrySet()) {
                // key对应 fieldName
                insertSqlFields.add(fieldValue.getKey().toLowerCase());
                insertSqlValues.add(PLACE_HOLDER);
                sqlParam.add(fieldValue.getValue());
            }
            sqlBuilder.append(insertSqlFields);
            sqlBuilder.append(insertSqlValues);
            List<List<Object>> recordParamList = resMap.computeIfAbsent(sqlBuilder.toString(), s -> new ArrayList<>());
            recordParamList.add(sqlParam);
        }
        return resMap;
    }

    /*验证sql中是否有占位符*/
    public static boolean hasPlaceholder(String sql) {
        if (StringUtils.hasText(sql) && sql.contains(PLACE_HOLDER)) {
            return true;
        }
        return false;
    }
}

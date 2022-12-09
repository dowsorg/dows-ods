package org.dows.ods.biz.util;

import cn.hutool.core.collection.CollUtil;

import java.util.*;

public class TableDataSqlUtil {

    //根据Map<String, Map<String, Object>> 生成插入数据的sql语句
    // key:table,value:insert tableName()
    public static Map<String, List<String>> generateSqlMapByTableFieldValueMap(Map<String, Map<String, Object>> tableFieldValueMap) {
        Map<String, List<String>> resMap = new HashMap<>();
        if (CollUtil.isNotEmpty(tableFieldValueMap)) {
            for (Map.Entry<String, Map<String, Object>> entry : tableFieldValueMap.entrySet()) {
                String tableName = JsonParseUtil.getTableNameFromCombineId(entry.getKey());
                List<String> sqlList = resMap.computeIfAbsent(tableName, s -> new ArrayList<>());
                // 开始
                Map<String, Object> record = entry.getValue();
                generateInsertSqlStrFromFieldValueMap(tableName, record, sqlList);
            }
        }
        return resMap;
    }

    public static void generateInsertSqlStrFromFieldValueMap(String tableName, Map<String, Object> record,
        List<String> sqlList) {
        if (!record.isEmpty()) {
            StringBuilder sqlBuilder = new StringBuilder(" insert into ");
            sqlBuilder.append(tableName);
            StringJoiner insertSqlFields = new StringJoiner(",","(",")");
            StringJoiner insertSqlValues = new StringJoiner("','","values('","')");
            for (Map.Entry<String, Object> entry : record.entrySet()) {
                // key对应 fieldName
                insertSqlFields.add(entry.getKey());
                // value 对应 fieldValue
                insertSqlValues.add(String.valueOf(entry.getValue()));
            }
            sqlBuilder.append(insertSqlFields);
            sqlBuilder.append(insertSqlValues);
            sqlList.add(sqlBuilder.toString());
        }
    }
}

package org.dows.ods.biz.util;

import org.springframework.util.StringUtils;

import java.util.StringJoiner;

/**
 * 组合ID工具类
 */
public class CombineIdUtil {
    /*组合Id的拼接和解析的分隔符*/
    private static final String COMBINE_ID_DELIMITER = "+";

    /*从组合ID中接续出表名*/
    public static String getTableNameFromCombineId(final String combineId) {
        if (StringUtils.hasText(combineId)) {
            // 截取到第一个分隔符为止的字符串为表名
            return combineId.substring(0, combineId.indexOf(COMBINE_ID_DELIMITER));
        }
        throw new IllegalArgumentException("组合ID没有字符内容");
    }

    /**
     * 输入前缀key和其他Id以"+"按顺序拼接来获取组合ID
     * */
    public static String generateCombineId(String prefixKey, String ... otherKeys){
        StringJoiner combineIdBuilder = new StringJoiner(COMBINE_ID_DELIMITER);
        combineIdBuilder.add(prefixKey);
        for (Object key : otherKeys) {
            // 将表的primaryKey 对应的字段值获取
            combineIdBuilder.add(String.valueOf(key));
        }
        return combineIdBuilder.toString();
    }
}

package org.dows.ods.biz.util;

import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.util.StringUtils;

/**
 * 数据库工具类
 * */
public class DbUtil {
    /**
     * 协议类型
     */
    private static final String PROTOTYPE = "jdbc";
    /**
     * 默认端口
     */
    private static final String DEFAULT_PORT = "3306";
    /**
     * 连接额外参数
     */
    private static final String CONNECTION_PARAM = "serverTimezone=GMT%2B8&autoReconnect=true&allowMultiQueries=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&rewriteBatchedStatements=true ";


    public static String generateDbUrlStrByParam(String host, String port, String dbName) {
        if (!StringUtils.hasText(host)) {
            throw new IllegalArgumentException("数据库连接参数异常");
        }
        if (!StringUtils.hasText(dbName)) {
            throw new IllegalArgumentException("数据库名参数异常");
        }
        // 准备端口
        port = StringUtils.hasText(port) ? port : DEFAULT_PORT;
        return String.format("%s:%s://%s:%s/%s?%s", PROTOTYPE, DbType.MYSQL.getDb(), host, port, dbName, CONNECTION_PARAM);
    }
}

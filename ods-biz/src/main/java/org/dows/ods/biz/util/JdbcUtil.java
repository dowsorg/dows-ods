package org.dows.ods.biz.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
    // 关闭连接
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (null != connection) {
                connection.close();
            }
            if (null != statement) {
                statement.close();
            }
            if (null !=resultSet){
                resultSet.close();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

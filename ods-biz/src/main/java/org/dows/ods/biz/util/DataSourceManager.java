package org.dows.ods.biz.util;

import cn.hutool.core.collection.CollUtil;
import com.mysql.cj.jdbc.Driver;
import org.dows.framework.api.exceptions.BizException;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**数据库连接工具*/
public class DataSourceManager {
    public static Connection getConnection(String url, String userName, String password) throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection(url, userName, password);
        if (Objects.isNull(connection)) {
            throw new BizException("数据库连接失败");
        }
        // 关闭自动提交
        connection.setAutoCommit(false);
        return connection;
    }

    public static void batchExecute(Connection connection, Map<String, List<List<Object>>> sqlParamMap)
        throws SQLException {
        if (Objects.isNull(connection) || CollUtil.isEmpty(sqlParamMap)) {
            return;
        }
        try {
            for (Map.Entry<String, List<List<Object>>> entry : sqlParamMap.entrySet()) {
                String sql = entry.getKey();
                List<List<Object>> sqlParams = entry.getValue();
                if (!StringUtils.hasText(sql)||CollUtil.isEmpty(sqlParams)) {
                    // 如果该条sql为空字符串或该条sql的所有参数列表为空，就继续下一条sql
                    continue;
                }
                PreparedStatement ps = connection.prepareStatement(sql);
                // 参数替换
                for (List<Object> sqlParam : sqlParams) {
                    if (CollUtil.isEmpty(sqlParam)) {
                        // 如果该条参数为空，则继续下一条记录
                        continue;
                    }
                    int i = 1;
                    for (Object o : sqlParam) {
                        ps.setObject(i++, o);
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            connection.commit();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    // 关闭连接
    public static void closeConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }
}

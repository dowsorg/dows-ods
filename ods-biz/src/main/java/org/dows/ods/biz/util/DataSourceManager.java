package org.dows.ods.biz.util;

import cn.hutool.core.collection.CollUtil;
import com.mysql.cj.jdbc.Driver;
import org.dows.framework.api.exceptions.BizException;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库连接工具
 */
public class DataSourceManager {
    /**
     * 数据库连接缓存
     * <p>
     * key(组合Id):url+userName+password
     */
    private static final Map<String, Connection> DB_CONNECTION_CACHES = new ConcurrentHashMap<>(1000);

    /**
     * 获取并更新缓存的数据库连接
     */
    public static synchronized Connection getConnection(String url, String userName, String password)
        throws SQLException {
        // 生成组合ID
        String combinedId = CombineIdUtil.generateCombineId(url, userName, password);
        // 如若缓存中已经存在，则直接使用已缓存的数据库连接
        // 如若不存在，就放入
        Connection connection = createConnection(url, userName, password);
        return DB_CONNECTION_CACHES.computeIfAbsent(combinedId, v -> connection);
    }

    public static synchronized Connection createConnection(String url, String userName, String password)
        throws SQLException {
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
        PreparedStatement ps = null;
        try {
            for (Map.Entry<String, List<List<Object>>> entry : sqlParamMap.entrySet()) {
                String sql = entry.getKey();
                List<List<Object>> sqlParams = entry.getValue();
                if (!StringUtils.hasText(sql) || CollUtil.isEmpty(sqlParams)) {
                    // 如果该条sql为空字符串或该条sql的所有参数列表为空，就继续下一条sql
                    continue;
                }
                ps = connection.prepareStatement(sql);
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
            JdbcUtil.close(connection,ps,null);
        }
    }

    // 执行单条 sql 并获取结果集
    public static ResultSet queryBySingleSql(Connection connection, String sql, List<Object> queryParams) {
        if (Objects.isNull(connection)) {
            throw new BizException("数据库连接失败");
        }
        if (!StringUtils.hasText(sql)) {
            throw new BizException("查询语句为空");
        }
        if (SqlUtil.hasPlaceholder(sql) && CollUtil.isEmpty(queryParams)) {
            // 如果sql中有占位符但是参数列表却为空，报错
            throw new BizException("sql 参数列表为空");
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            // 参数替换
            int i = 1;
            for (Object sqlParam : queryParams) {
                ps.setObject(i++, sqlParam);
            }
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new BizException(e.getMessage());
        } finally {
            JdbcUtil.close(null,ps,rs);
        }
    }
}

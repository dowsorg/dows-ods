package org.dows.ods.biz.util;

import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.entity.SchoolTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * sql 查询结果集的解析工具
 */
public class ResultSetUtil {

    public static List<SchoolTable> createSchoolTableListFromInformationSchemaTablesQuery(ResultSet rs) {
        try {
            List<SchoolTable> list = new ArrayList<>(rs.getRow());
            while (rs.next()) {
                list.add(createSchoolTableFromInformationSchemaTablesQuery(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new BizException(e.getMessage());
        }
    }

    public static SchoolTable createSchoolTableFromInformationSchemaTablesQuery(ResultSet rs) {
        try {
            SchoolTable bean = new SchoolTable();
            bean.setDbName(rs.getString("TABLE_SCHEMA"));
            bean.setTableName(rs.getString("TABLE_NAME"));
            bean.setTableDescr(rs.getString("TABLE_COMMENT"));
            return bean;
        } catch (SQLException e) {
            throw new BizException(e.getMessage());
        }
    }
}

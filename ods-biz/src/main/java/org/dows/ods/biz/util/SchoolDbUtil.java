package org.dows.ods.biz.util;

import org.dows.ods.entity.SchoolDb;

/**
 * 学校数据库实例的工具类
 */
public class SchoolDbUtil {

    /**
     * 根据输入SchoolDb示例生成mysql的url:string
     */
    public static String generateDbUrlBySchoolDbInstance(SchoolDb schoolDb) {
        return DbUtil.generateDbUrlStrByParam(schoolDb.getHost(), schoolDb.getPort(),schoolDb.getDbName());
    }
}

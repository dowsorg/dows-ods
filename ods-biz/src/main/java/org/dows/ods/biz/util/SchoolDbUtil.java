package org.dows.ods.biz.util;

import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.entity.SchoolDb;
import org.dows.ods.vo.SchoolDbCreateVO;
import org.springframework.util.StringUtils;

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

    /**创建schoolDb的验证方法*/
    public static void validate(SchoolDbCreateVO createVO){
        if(!StringUtils.hasText(createVO.getSchoolId())){
            throw new BizException("必须指定学校");
        }
        if(!StringUtils.hasText(createVO.getAppId())){
            throw new BizException("必须指定学校应用");
        }
        if(!StringUtils.hasText(createVO.getDbName())){
            throw new BizException("必须填写数据库名称");
        }
        if(!StringUtils.hasText(createVO.getDbUser())){
            throw new BizException("必须填写数据库用户名");
        }
        if(!StringUtils.hasText(createVO.getDbPwd())){
            throw new BizException("必须填写数据库密码");
        }
    }
}

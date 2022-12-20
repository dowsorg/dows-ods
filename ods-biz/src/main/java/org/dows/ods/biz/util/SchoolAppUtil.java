package org.dows.ods.biz.util;

import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.vo.SchoolAppCreateVO;
import org.springframework.util.StringUtils;

/**
 * 学校应用实例的工具类
 * */
public class SchoolAppUtil {
    /**创建schoolApp的验证方法*/
    public static void validate(SchoolAppCreateVO createVO){
        if(!StringUtils.hasText(createVO.getSchoolId())){
            throw new BizException("必须指定学校");
        }
//        if(!StringUtils.hasText(createVO.getAppId())){
//            throw new BizException("必须指定学校应用");
//        }
        if(!StringUtils.hasText(createVO.getAppName())){
            throw new BizException("必须填写应用名称");
        }
    }
}
package org.dows.ods.biz.util;

import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.vo.SchoolApiCreateVO;
import org.springframework.util.StringUtils;

/**
 * 学校Api工具类
 */
public class SchoolApiUtil {
    /**创建schoolApi的验证方法*/
    public static void validate(SchoolApiCreateVO createVO){
        if(!StringUtils.hasText(createVO.getSchoolId())){
            throw new BizException("必须指定学校");
        }
        if(!StringUtils.hasText(createVO.getAuthId())){
            throw new BizException("必须指定学校token获取方式");
        }
        if(!StringUtils.hasText(createVO.getApiUrl())){
            throw new BizException("必须填写Api的url");
        }
        String httpMethod = createVO.getHttpMethod();
        if(!StringUtils.hasText(httpMethod)){
            throw new BizException("必须填写Api的请求方式");
        }
        if(!HttpManager.validateHttpMethod(httpMethod)){
            throw new BizException("不支持填写的请求方式");
        }
    }
}

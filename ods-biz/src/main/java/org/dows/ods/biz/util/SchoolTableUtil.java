package org.dows.ods.biz.util;

import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.vo.SchoolTableCreateVO;
import org.springframework.util.StringUtils;

/**
 * 学校数据库表的工具类
 */
public class SchoolTableUtil {

    /**创建schoolTable的验证方法*/
    public static void validate(SchoolTableCreateVO createVO){
        if(!StringUtils.hasText(createVO.getAppId())){
            throw new BizException("必须指定学校应用");
        }
        if(!StringUtils.hasText(createVO.getDbId())){
            throw new BizException("必须指定数据库");
        }
        if(!StringUtils.hasText(createVO.getTableName())){
            throw new BizException("必须填写数据表名");
        }
    }
}

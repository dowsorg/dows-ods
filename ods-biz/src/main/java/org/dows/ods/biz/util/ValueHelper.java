package org.dows.ods.biz.util;

import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.bo.ValueConfig;

import java.util.Objects;

/**字段值替换方案的验证规则*/
public class ValueHelper {
    public static void validate(ValueConfig valueConfig)throws BizException {
        // 如若设置了，则结果集中的字段值和表中字段值都要填写
        if(Objects.isNull(valueConfig.getJsonFieldValue())){
            throw new BizException("json中替换前的字段值必须填写");
        }
        if(Objects.isNull(valueConfig.getTableFieldValue())){
            throw new BizException("替换后的字段值必须填写");
        }
    }
}

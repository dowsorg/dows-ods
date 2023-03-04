package org.dows.ods.biz.util;

import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.bo.CryptoConfig;
import org.dows.ods.bo.FieldConfig;
import org.dows.ods.bo.ValueConfig;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/*字段解析规则的辅助类*/
public class FieldHelper {
    /**验证解析规则完整性*/
    public static void validate(FieldConfig fieldConfig)throws BizException{
        // jsonFieldName 可为空
        // tableFieldName不可为空，todo 对应还需要验证是否存在该字段吗?
        if(!StringUtils.hasText(fieldConfig.getTableFieldName())){
            throw new BizException("数据表字段名必须填写");
        }
        // 默认值不可为空
        if(Objects.isNull(fieldConfig.getDefaultValue())){
            throw new BizException("字段的默认值必须填写");
        }
        //是否是表的主键可以不填写；
        // 是否需要对字段值加密的标记在为true时 要和 加密信息 成对出现;
        if(Boolean.TRUE.equals(fieldConfig.getEncryptValueTag())){
            CryptoConfig cryptoConfig = fieldConfig.getCryptoConfig();
            if(Objects.isNull(cryptoConfig)){
                throw new BizException("加密标记已开启，必须填写加密信息");
            }
            if(!StringUtils.hasText(cryptoConfig.getCryptoType())){
                throw new BizException("加密方式不完整");
            }
            if(!CryptoHelper.getCryptoTypeAlgorithm().containsKey(cryptoConfig.getCryptoType())){
                throw new BizException("不支持该加密方式");
            }
        }
        //字段值的规则集合 ValueConfig 可以不填写
        // 如若填写，则调用 ValueHelper 的验证方法
        List<ValueConfig> valueConfigs = fieldConfig.getValueConfigs();
        if(!CollectionUtils.isEmpty(valueConfigs)){
            for (ValueConfig valueConfig : valueConfigs) {
                ValueHelper.validate(valueConfig);
            }
        }
    }
}

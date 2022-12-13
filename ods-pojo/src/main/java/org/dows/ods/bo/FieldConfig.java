package org.dows.ods.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 字段
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldConfig {
    /*结果集中的字段名:若有特殊需要添加结果集中没有但是表中需要的功能字段，设置为null或者""即可*/
    @ApiModelProperty(value = "结果集中的字段名",notes = "若有特殊需要添加结果集中没有但是表中需要的功能字段，设置为null或者\"\"即可",required = false)
    private String jsonFieldName;
    @ApiModelProperty(value = "对应数据表中的字段名",notes = "",required = true)
    private String tableFieldName;
    @ApiModelProperty(value = "字段值的规则集合",notes = "",required = true)
    private List<ValueConfig> valueConfigs;
    @ApiModelProperty(value = "默认值",notes = "",required = true)
    private Object defaultValue;
    @ApiModelProperty(value = "是否是表的主键",notes = "可不填，就没有对字段的主键处理",required = false)
    private Boolean primaryKeyTag;
    // todo 是否需要对字段值加密的标记
    @ApiModelProperty(value = "是否需要对字段值加密的标记",notes = "可不填，但是要和加密信息成对出现",required = false)
    private Boolean encryptValueTag;
    // todo 加密信息(加密的密钥等)
    @ApiModelProperty(value = "加密信息(加密的密钥等)",notes = "可不填，但是要和字段值加密的标记成对出现",required = false)
    private CryptoConfig cryptoConfig;

}
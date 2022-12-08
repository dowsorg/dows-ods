package org.dows.ods.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校接口token
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolTokenCreateVO {
    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("账号名")
    private String appKey;

    @ApiModelProperty("密钥")
    private String secretKey;

    @ApiModelProperty("tokenApi")
    private String tokenApi;

    @ApiModelProperty("json参数")
    private String jsonParam;
}

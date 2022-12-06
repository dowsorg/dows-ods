package org.dows.ods.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 学校Token(SchoolToken)表单
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:56
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SchoolTokenForm 表单对象", description = "学校Token")
public class SchoolTokenForm implements Serializable {
    private static final long serialVersionUID = -31362077420199926L;
    @JsonIgnore
    private Long id;

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

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}


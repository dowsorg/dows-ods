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
 * 学校维护人员(SchoolMaintainer)表单
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:55
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SchoolMaintainerForm 表单对象", description = "学校维护人员")
public class SchoolMaintainerForm implements Serializable {
    private static final long serialVersionUID = -39183655213699219L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("维护人员类型[销售，售后，技术支持]")
    private String maintainerType;

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}


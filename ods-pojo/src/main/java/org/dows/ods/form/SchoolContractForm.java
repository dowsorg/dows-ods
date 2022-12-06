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
 * 学校联系信息(SchoolContract)表单
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
@ApiModel(value = "SchoolContractForm 表单对象", description = "学校联系信息")
public class SchoolContractForm implements Serializable {
    private static final long serialVersionUID = -44798655740764715L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("联系人")
    private String contracter;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("电话")
    private String telphone;

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}


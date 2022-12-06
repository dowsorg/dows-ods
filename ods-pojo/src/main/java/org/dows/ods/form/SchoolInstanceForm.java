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
 * 学校(SchoolInstance)表单
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
@ApiModel(value = "SchoolInstanceForm 表单对象", description = "学校")
public class SchoolInstanceForm implements Serializable {
    private static final long serialVersionUID = -82513852424835425L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("学校名称")
    private String schoolName;

    @ApiModelProperty("学校code")
    private String schoolCode;

    @ApiModelProperty("学校官网")
    private String schoolHome;

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}


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
 * 学校应用(SchoolApp)表单
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
@ApiModel(value = "SchoolAppForm 表单对象", description = "学校应用")
public class SchoolAppForm implements Serializable {
    private static final long serialVersionUID = 224663041336270736L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("学校名称")
    private String schoolName;

    @ApiModelProperty("应用名称")
    private String appName;

    @ApiModelProperty("应用主页")
    private String appHome;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty("应用供应商")
    private String appProvider;

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}


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
 * 学校接口(SchoolApi)表单
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
@ApiModel(value = "SchoolApiForm 表单对象", description = "学校接口")
public class SchoolApiForm implements Serializable {
    private static final long serialVersionUID = 312680280683273875L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("账号ID")
    private String authId;

    @ApiModelProperty("http请求方法")
    private String httpMethod;

    @ApiModelProperty("学校API")
    private String apiUrl;

    @ApiModelProperty("json参数")
    private String jsonParams;

    @ApiModelProperty("接口描述")
    private String apiDescr;

    @ApiModelProperty("提取的key[key1,key2,key3]")
    private String extractKey;

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}


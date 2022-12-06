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
 * 学校db(SchoolDb)表单
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
@ApiModel(value = "SchoolDbForm 表单对象", description = "学校db")
public class SchoolDbForm implements Serializable {
    private static final long serialVersionUID = 334686363521944019L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("数据库ID")
    private String dbId;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("学习ID")
    private String schoolId;

    @ApiModelProperty("应用名")
    private String appName;

    @ApiModelProperty("数据库名")
    private String dbName;

    @ApiModelProperty("数据库用户名")
    private String dbUser;

    @ApiModelProperty("数据库密码")
    private String dbPwd;

    @ApiModelProperty("IP地址")
    private String host;

    @ApiModelProperty("端口")
    private String port;

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}


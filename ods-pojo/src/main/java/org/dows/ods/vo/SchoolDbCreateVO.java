package org.dows.ods.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校db创建
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDbCreateVO {
    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("学校ID")
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
}

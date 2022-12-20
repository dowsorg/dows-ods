package org.dows.ods.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校db创建
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDbCreateVO {
    @ApiModelProperty(value = "应用ID",required = true)
    private String appId;

    @ApiModelProperty(value = "学校ID",required = true)
    private String schoolId;

    @ApiModelProperty(value = "应用名",notes ="不填写的话会由后端获取",required = false)
    private String appName;

    @ApiModelProperty(value = "数据库名",required = true)
    private String dbName;

    @ApiModelProperty(value = "数据库用户名",required = true)
    private String dbUser;

    @ApiModelProperty(value = "数据库密码",required = true)
    private String dbPwd;

    @ApiModelProperty(value = "IP地址",required = true)
    private String host;

    @ApiModelProperty(value = "端口",notes = "不填写的情况下就默认为3306",required = false)
    private String port;
}

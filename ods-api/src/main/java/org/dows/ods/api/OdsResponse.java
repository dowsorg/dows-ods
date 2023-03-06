package org.dows.ods.api;

import lombok.Data;

@Data
public class OdsResponse {
    // 应用ID
    private String appId;
    // 应用code
    private String appCode;
    // 应用名
    private String appName;
    // host
    private String host;
    // 端口
    private String port;
    // 用户名
    private String userName;
    // 数据库密码
    private String pwd;
    // 业务数据
    private String bizJson;

}

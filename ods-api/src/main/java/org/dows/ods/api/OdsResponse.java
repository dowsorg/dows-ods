package org.dows.ods.api;

import lombok.Data;

@Data
public class OdsResponse {
    // 应用ID
    private String appId;
    // 应用code
    private String appCode;
    // 应用名
//    private String appName;
//    // 数据库或系统的host
//    private String host;
//    // 数据库或系统的端口
//    private String port;
//    // 用户名
//    private String userName;
//    // 数据库或系统的：pwd/token
//    private String token;
    // 希望执行的方式： put：update/delete:delete/get:select/post:insert
//    private String method;
    // 通道
//    private String channel;
//    // 希望执行的端点
//    private String endpoint;
    // 业务数据
    private String bizJson;

}

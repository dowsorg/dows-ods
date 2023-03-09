package org.dows.ods.channel.hnilab;

import lombok.Data;

/**
 * "appid": "平台分配的 appid",
 *  "ticket": "获取到的项目 ticket",
 *  "signature": "签名 MD5(appid+secret+ticket)"
 */
@Data
public class HnilabGetTokenRequest {
    private String appId;
    private String ticket;
    private String signature;

}

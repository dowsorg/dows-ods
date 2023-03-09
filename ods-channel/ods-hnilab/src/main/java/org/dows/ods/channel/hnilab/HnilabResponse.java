package org.dows.ods.channel.hnilab;

import lombok.Data;

@Data
public class HnilabResponse {

    private String success;
    private String code;
    private String total;
    private Rows rows;
    private String msg;


    /**
     * "rows":{"accesstoken": "token","expiresin": "过期时长 秒
     * ","tokentype": "Bearer"},
     */
    @Data
    public static class Rows {
        private String accesstoken;
        private String expiresin;
        private String tokentype;
    }
}

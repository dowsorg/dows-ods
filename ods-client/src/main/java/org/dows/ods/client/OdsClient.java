package org.dows.ods.client;

import org.dows.framework.api.Response;
import org.dows.ods.api.OdsResponse;

public class OdsClient {

    public static Response exec(OdsPointcutProperties.Endpoint endpoint, OdsResponse odsResponse) {
        String type = endpoint.getType();
        // todo 执行jdbc 方式
        if (type.equals("jdbc")) {
            // todo 动态创建数据源

        } else if (type.equals("http")) { // todo 执行http 方式


        }
        return Response.ok();
    }
}

package org.dows.ods.client;

import org.dows.framework.api.Response;
import org.dows.ods.api.OdsResponse;
import org.dows.ods.channel.hnilab.HnilabApi;
import org.dows.ods.channel.hnilab.HnilabConfig;

public class OdsExecutor {


    public static Response exec(String env,OdsPointcutProperties.Endpoint endpoint, OdsResponse odsResponse) {
        // 根据环境获取对应的接口url
        HnilabApi hnilabApiUrl = HnilabConfig.getHnilabApiUrl(env);

        String endpoint1 = odsResponse.getEndpoint();

        //hnilabApiUrl.
        String type = endpoint.getType();
        // todo 执行jdbc 方式
        if (type.equals("jdbc")) {
            // todo 动态创建数据源

        } else if (type.equals("http")) { // todo 执行http 方式


        }
        return Response.ok();
    }
}

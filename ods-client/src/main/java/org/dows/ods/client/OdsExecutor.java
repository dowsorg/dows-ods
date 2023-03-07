package org.dows.ods.client;

import org.dows.framework.api.Response;
import org.dows.ods.api.ChannelConfig;
import org.dows.ods.api.OdsResponse;
import org.dows.ods.api.ChannelApi;
import org.dows.ods.api.ChannelSetting;
import org.dows.ods.channel.hnilab.HnilabConfig;

import java.util.Map;

public class OdsExecutor {


    public static Response exec(ChannelConfig channelConfig, OdsResponse odsResponse) {
        // 动态根据环境获取对应的接口对象，包含了环境对应的api
        ChannelApi channelApi = channelConfig.getChannelApi();
        // 获取接口uri 有可能是http 也有可能是 jdbc
        String apiUri = channelApi.getApiUriByMethodName(odsResponse.getMethod());

        String type = channelConfig.getDatasourceType();
        // todo 执行jdbc 方式
        if (type.equals("jdbc")) {
            // todo 根据apiUri 动态创建数据源,并执行

        } else if (type.equals("http")) {
            // todo 根据apiUri 执行http 方式

        }
        return Response.ok();
    }
}

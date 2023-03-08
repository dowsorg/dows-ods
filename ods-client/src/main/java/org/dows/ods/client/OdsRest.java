package org.dows.ods.client;

import org.dows.framework.api.Response;
import org.dows.ods.api.ChannelApi;
import org.dows.ods.api.ChannelConfig;
import org.dows.ods.api.ChannelProperties;
import org.springframework.web.bind.annotation.PostMapping;

public class OdsRest {
    private ChannelConfig channelConfig;

    @PostMapping()
    public Response  register(String ticket,String token){

        ChannelApi channelApi = channelConfig.getChannelApi();
        String getUserInfo = channelApi.getApiUriByMethodName("getUserInfo");
        //"https://www.hnilab.com/api/OpenData/GetToken?AppId=mmmm&AppSecret=nnnn&ticket=ddd&token=cccc"
        String http = getUserInfo + "&ticket=ddd&token=cccc";


        return Response.ok();

    }
}

package org.dows.ods.api;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ChannelSetting {
    private String id;
    private String channel;
    private String type;
    private String enable;
    private String env;
    private String appId;
    private String appSecret;

    private Map<String,String> apis;

    private List<ChannelEnv> envs;



    public String getKey(String env){
        return env+":"+this.getChannel()+":"+this.getType();
    }

}
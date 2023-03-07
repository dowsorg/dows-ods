package org.dows.ods.api;

import lombok.Data;

import java.util.List;

@Data
public class ChannelSetting {
    private String id;
    private String type;
    private String enable;
    private String env;
    private String appId;
    private String appSecret;

    private ChannelApi hnilabApi;

    private List<ChannelEnv> channelEnvs;
}
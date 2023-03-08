package org.dows.ods.channel.hnilab;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.dows.ods.api.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
//@EnableConfigurationProperties(ChannelProperties.class)
@Configuration("hnilab")
public class HnilabConfig implements ChannelConfig {
    private static final Map<String, ChannelApi> channelEnvApiMap = new HashMap<>();
    @Getter
    private final ChannelProperties channelProperties;

    private final String channel = "hnilab";

    @Override
    public String getChannelName() {
        return channel;
    }

//    @Override
//    public String getDatasourceType() {
//        ChannelSetting channelSetting = channelProperties.getChannels().get(channel);
//        if(channelSetting != null) {
//            return channelSetting.getType();
//        }
//        return null;
//    }

//    @PostConstruct
//    public void init() {
//        Map<String, Field> fieldMap = HnilabApi.getFieldMap();
//        Map<String, ChannelSetting> channels = channelProperties.getChannels();
//        ChannelSetting channelSetting = channels.get(channel);
//
//        List<ChannelEnv> envs = channelSetting.getEnvs();
//
//        for (ChannelEnv env : envs) {
//            ChannelApi hnilabApi = extracted(fieldMap, env.getHost());
//            channelEnvApiMap.put(channel+":"+env.getName(), hnilabApi);
//        }
//    }

    public ChannelApi getChannelApi() {
//        Map<String, ChannelSetting> channels = channelProperties.getChannels();
//        ChannelSetting channelSetting = channels.get(channel);
//        ChannelApi hnilabApi = channelEnvApiMap.get(channel+":"+channelSetting.getEnv());
//        if (hnilabApi == null) {
//            throw new RuntimeException("不存在该环境对应的接口配置，请检查环境配置");
//        }
        return new HnilabApi();
    }



//    public static ChannelApi getHnilabApi(String env) {
//        ChannelApi hnilabApi = hnilabApiMap.get(env);
//        if (hnilabApi == null) {
//            throw new RuntimeException("不存在该环境对应的接口配置，请检查环境配置");
//        }
//        return hnilabApi;
//    }




}

package org.dows.ods.channel.hnilab;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@EnableConfigurationProperties(HnIlabProperties.class)
@Configuration
public class HnilabConfig {
    @Getter
    private final HnIlabProperties hnIlabProperties;

    private static final Map<String,HnilabApi> hnilabApiMap = new HashMap<>();
    @PostConstruct
    public void init() {
        Map<String, Field> fieldMap = HnilabApi.getFieldMap();
        List<HnIlabProperties.Env> envs = hnIlabProperties.getEnvs();
        for (HnIlabProperties.Env env : envs) {
            HnilabApi hnilabApi = extracted(fieldMap, env.getHost());
            hnilabApiMap.put(env.getName(),hnilabApi);
        }
    }


    public  HnilabApi getHnilabApiUrl(){
        HnilabApi hnilabApi = hnilabApiMap.get(hnIlabProperties.getEnv());
        if(hnilabApi == null){
            throw new RuntimeException("不存在该环境对应的接口配置，请检查环境配置");
        }
        return hnilabApi;
    }
    public static HnilabApi getHnilabApiUrl(String env){
        HnilabApi hnilabApi = hnilabApiMap.get(env);
        if(hnilabApi == null){
            throw new RuntimeException("不存在该环境对应的接口配置，请检查环境配置");
        }
        return hnilabApi;
    }

    private  HnilabApi extracted(Map<String, Field> fieldMap, String host) {
        HnilabApi hnilabApi = hnIlabProperties.getHnilabApi();
        String appId = hnIlabProperties.getAppId();
        String appSecret = hnIlabProperties.getAppSecret();
        Set<String> fieldNames = fieldMap.keySet();
        for (String field : fieldNames) {
            Field field1 = fieldMap.get(field);
            try {
                String uri = (String) field1.get(hnilabApi);
                String url = host + uri + "?appId = " + appId + "&appSecret = " + appSecret;
                field1.setAccessible(true);
                // 动态修改
                field1.set(hnilabApi,url);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return hnilabApi;
    }

}

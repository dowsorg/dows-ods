package org.dows.ods.client;


import lombok.Data;
import org.dows.framework.api.util.YamlPropertySourceFactory;
import org.dows.ods.api.*;
import org.dows.ods.channel.hnilab.HnilabConfig;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@PropertySource(value = "classpath:application-ods.yml", factory = YamlPropertySourceFactory.class)
//@EnableConfigurationProperties({ChannelProperties.class})
@Import(HnilabConfig.class)
@Configuration
public class OdsConfig {

    private final Map<String, ChannelConfig> channelConfigMap;
    private final ChannelProperties channelProperties;

    // 端点通道对应的通道配置 key:端点名，值端点对应的配置
    private final Map<String, ChannelSetting> channelSettingMap = new HashMap<>();
    private final Map<String, ChannelApi> channelEnvApiMap = new HashMap<>();
    private final Map<String, ChannelProperties.Pointcut> pointcutMap = new HashMap<>();
    @PostConstruct
    public void init(){
        for (ChannelSetting channelSetting : channelProperties.getChannelSettings()) {
            channelSettingMap.put(channelSetting.getId(),channelSetting);
            ChannelConfig channelConfig = channelConfigMap.get(channelSetting.getId());
            if(channelConfig== null){
                continue;
            }
            List<ChannelEnv> envs = channelSetting.getEnvs();
            for (ChannelEnv env : envs) {
                // 创建不同环境的 channelApi
                ChannelApi channelApi = extracted(channelSetting, channelConfig, env);
                channelEnvApiMap.put(env.getName() + ":" + channelConfig.getChannelName(), channelApi);
            }
        }

        /**
         * 构建pointcut
         */
        List<ChannelProperties.Pointcut> pointcuts = channelProperties.getPointcuts();
        for (ChannelProperties.Pointcut pointcut : pointcuts) {
            Class clazz = pointcut.getClazz();
            List<ChannelProperties.Method> methods = pointcut.getMethods();
            for (ChannelProperties.Method method : methods) {
                String pmethod = clazz+"."+method.getName();
                pointcutMap.put(pmethod,pointcut);
            }
        }
    }


    private ChannelApi extracted(ChannelSetting channelSetting, ChannelConfig channelConfig, ChannelEnv env) {
        String appId = channelSetting.getAppId();
        String appSecret = channelSetting.getAppSecret();

        // 获取原始接口
        Map<String, String> apis = channelSetting.getApis();
        // 获取接口对象
        ChannelApi channelApi = channelConfig.getChannelApi();
        // 动态mapping 为channelApi 具体对象设值
        Map<String, Field> fieldMap = channelApi.getFieldMap();

        // 组装
        apis.forEach((k,v)->{
            String method = channelSetting.getType() + "://" + k;
            Field field1 = fieldMap.get(k);
            try {
                if(field1 != null) {
                    // 原始uri
                    String orgUri = apis.get(k);
                    // 组装完成
                    String url = env.getHost() + orgUri + "?appId = " + appId + "&appSecret = " + appSecret;
                    field1.setAccessible(true);
                    // 动态赋值
                    field1.set(channelApi, url);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return  channelApi;

//        Map<String, ChannelSetting> channels = channelProperties.getChannels();
//        ChannelSetting channelSetting = channels.get(channel);
//        HnilabApi hnilabApi = new HnilabApi();
//        Map<String, String> apis = channelSetting.getApis();
        //ChannelApi hnilabApi = channelSetting.getChannelApi();
//        String appId = channelSetting.getAppId();
//        String appSecret = channelSetting.getAppSecret();
//        Set<String> fieldNames = fieldMap.keySet();
//        for (String field : fieldNames) {
//            Field field1 = fieldMap.get(field);
//            try {
//                String uri = apis.get(field);
//                String url = host + uri + "?appId = " + appId + "&appSecret = " + appSecret;
//                field1.setAccessible(true);
//                // 动态修改
//                field1.set(hnilabApi, url);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return hnilabApi;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties("dows.ods")
    public ChannelProperties channelProperties(){
        return new ChannelProperties();
    }
    @Bean("odsPointcut")
    NameMatchMethodPointcut nameMatchMethodPointcut(){
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        List<ChannelProperties.Pointcut> pointcuts = channelProperties().getPointcuts();
        for (ChannelProperties.Pointcut pointcut : pointcuts) {
            List<ChannelProperties.Method> methods = pointcut.getMethods();
            List<String> collect = methods.stream().map(ChannelProperties.Method::getName).collect(Collectors.toList());
            for (String s : collect) {
                nameMatchMethodPointcut.addMethodName(s);
            }
        }
        // todo 这里需要重写ClassFilter
        nameMatchMethodPointcut.setClassFilter(clazz -> {
            List<Class> collect = channelProperties().getPointcuts().stream()
                    .map(ChannelProperties.Pointcut::getClazz).collect(Collectors.toList());
            if(collect.contains(clazz)){
                return true;
            }
            return false;
        });
        return nameMatchMethodPointcut;
    }

    @Bean("odsAroundMethod")
    AroundMethod aroundMethod(){
        AroundMethod aroundMethod = new AroundMethod();
        aroundMethod.setChannelConfig(channelConfigMap);
        aroundMethod.setPointcut(pointcutMap);
        aroundMethod.setChannelEnvApiMap(channelEnvApiMap);
        return aroundMethod;
    }

    @Bean("odsPointcutAdvisor")
    DefaultPointcutAdvisor defaultPointcutAdvisor(){
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setAdvice(aroundMethod());
        defaultPointcutAdvisor.setPointcut(nameMatchMethodPointcut());
        return defaultPointcutAdvisor;
    }
}

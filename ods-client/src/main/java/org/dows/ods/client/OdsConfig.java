package org.dows.ods.client;


import lombok.Data;
import org.dows.framework.api.util.YamlPropertySourceFactory;
import org.dows.ods.api.ChannelConfig;
import org.dows.ods.api.ChannelProperties;
import org.dows.ods.channel.hnilab.HnilabConfig;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

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

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties("dows.ods")
    public ChannelProperties channelProperties(){
        return new ChannelProperties();
    }
    @Bean("odsPointcut")
    NameMatchMethodPointcut nameMatchMethodPointcut(){
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        List<ChannelProperties.Pointcut> pointcuts = channelProperties.getPointcuts();
        for (ChannelProperties.Pointcut pointcut : pointcuts) {
            // todo 这里需要重写ClassFilter
            nameMatchMethodPointcut.setClassFilter(clazz -> clazz.equals(pointcut.getClazz()));
            List<ChannelProperties.Method> methods = pointcut.getMethods();
            List<String> collect = methods.stream().map(ChannelProperties.Method::getName).collect(Collectors.toList());
            for (String s : collect) {
                nameMatchMethodPointcut.addMethodName(s);
            }
        }
        return nameMatchMethodPointcut;
    }

    @Bean("odsAroundMethod")
    AroundMethod aroundMethod(){
        AroundMethod aroundMethod = new AroundMethod();
        aroundMethod.setChannelConfig(channelConfigMap);
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

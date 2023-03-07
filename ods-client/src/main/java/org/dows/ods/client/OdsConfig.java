package org.dows.ods.client;


import lombok.Data;
import org.dows.framework.api.util.YamlPropertySourceFactory;
import org.dows.ods.channel.hnilab.HnIlabProperties;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.stream.Collectors;

@Data
@PropertySource(value = "classpath:application-ods.yml", factory = YamlPropertySourceFactory.class)
@EnableConfigurationProperties({OdsPointcutProperties.class})
@Configuration
public class OdsConfig {

    private final OdsPointcutProperties odsProperties;

    @Bean("odsPointcut")
    NameMatchMethodPointcut nameMatchMethodPointcut(){
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        List<OdsPointcutProperties.Pointcut> pointcuts = odsProperties.getPointcuts();
        for (OdsPointcutProperties.Pointcut pointcut : pointcuts) {
            // todo 这里需要重写ClassFilter
            nameMatchMethodPointcut.setClassFilter(clazz -> clazz.equals(pointcut.getClazz()));
            List<OdsPointcutProperties.Method> methods = pointcut.getMethods();
            List<String> collect = methods.stream().map(OdsPointcutProperties.Method::getName).collect(Collectors.toList());
            for (String s : collect) {
                nameMatchMethodPointcut.addMethodName(s);
            }
        }
        return nameMatchMethodPointcut;
    }

    @Bean("odsAroundMethod")
    AroundMethod aroundMethod(){
        AroundMethod aroundMethod = new AroundMethod(odsProperties);
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

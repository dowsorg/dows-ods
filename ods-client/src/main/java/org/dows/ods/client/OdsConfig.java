package org.dows.ods.client;


import lombok.Data;
import org.dows.framework.api.util.YamlPropertySourceFactory;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Data
@PropertySource(value = "classpath:application-ods.yml", factory = YamlPropertySourceFactory.class)
@EnableConfigurationProperties(OdsPointcutProperties.class)
@Configuration
public class OdsConfig {

    private final OdsPointcutProperties odsProperties;

    @Bean("odsPointcut")
    NameMatchMethodPointcut nameMatchMethodPointcut(){
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        OdsPointcutProperties.Pointcut pointcut = odsProperties.getPointcut();
        nameMatchMethodPointcut.setClassFilter(clazz -> clazz.equals(pointcut.getClazz()));
        nameMatchMethodPointcut.setMappedNames(pointcut.getMethods());
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

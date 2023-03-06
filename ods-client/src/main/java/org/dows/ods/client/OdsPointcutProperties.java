package org.dows.ods.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "dows.ods")
public class OdsPointcutProperties {

    private String endpoint;
    private Pointcut pointcut;
    @Data
    public static class Pointcut {
        private Class clazz;
        private String[] methods;
    }
}

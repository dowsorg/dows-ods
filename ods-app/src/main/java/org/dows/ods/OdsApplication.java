package org.dows.ods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author
 * @description 如果用多数据源，需要自定义配置，那么需要禁用springboot的自动装配 即 exclude = {DataSourceAutoConfiguration.class
 * @date 2022年12月6日 下午3:23:38
 */
@SpringBootApplication(scanBasePackages = {"org.dows.ods.*","org.dows.framework.crud.*"})
public class OdsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OdsApplication.class, args);
    }
}


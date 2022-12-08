package org.dows.ods.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author
 * @description
 * @date 2022年12月6日 下午3:23:38
 */
@SpringBootApplication(scanBasePackages = { "org.dows.ods" })
@MapperScan(value = "org.dows.ods.mapper")
public class OdsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OdsApplication.class, args);
    }
}


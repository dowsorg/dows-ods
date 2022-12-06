package org.dows.ods.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @description
*
* @author 
* @date 2022年12月6日 下午3:23:38
*/
@SpringBootApplication(scanBasePackages = {"org.dows.ods"})
public class OdsApplication{
    public static void main(String[] args) {
        SpringApplication.run(OdsApplication.class, args);
    }
}


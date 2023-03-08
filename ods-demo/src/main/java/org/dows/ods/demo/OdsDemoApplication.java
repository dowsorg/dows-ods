package org.dows.ods.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OdsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdsDemoApplication.class, args);
    }
}

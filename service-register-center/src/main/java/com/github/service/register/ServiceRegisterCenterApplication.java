package com.github.service.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * @author peach
 * @since 2020/11/26 15:29
 */
@EnableEurekaServer
@SpringBootApplication
public class ServiceRegisterCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegisterCenterApplication.class, args);
    }

}

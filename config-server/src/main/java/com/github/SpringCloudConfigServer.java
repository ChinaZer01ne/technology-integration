package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Zer01ne
 * @since 2021/8/22 22:56
 */
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServer {
    public static void main( String[] args ) {
        SpringApplication.run(SpringCloudConfigServer.class, args);
    }
}

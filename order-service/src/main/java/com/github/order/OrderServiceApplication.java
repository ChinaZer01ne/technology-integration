package com.github.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Zer01ne
 * @since 2020/11/28 23:29
 */
@EnableFeignClients(basePackages = "com.github.internal.api")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.github.order.mapper")
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}

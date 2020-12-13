package com.github.tx.configuration;

import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 消息驱动分布式事务
 * @author Zer01ne
 * @date 2020-01-17 16:36
 * @since 1.0
 * @version V1.0
 **/
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(RocketMQProperties.class)
public class RocketAutoConfiguration {


}

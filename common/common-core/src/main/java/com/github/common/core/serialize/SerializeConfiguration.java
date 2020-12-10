package com.github.common.core.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 配置全局序列化的两种方式：
 *  1、配置Jackson2ObjectMapperBuilderCustomizer
 *  2、配置ObjectMapper
 *      2.1、如果需要使用自动配置的方式，应该使用@AutoConfigureBefore({JacksonAutoConfiguration.class})，让ObjectMapper在JacksonAutoConfiguration之前注入进来
 *
 * 全局序列话的东西不一定每个微服务都要使用，独立于common包会更好。
 *
 * @author peach
 * @since 2020/12/1 15:40
 */
@AutoConfigureBefore({JacksonAutoConfiguration.class})
@Configuration
public class SerializeConfiguration {
    ///**
    // * 1、配置Jackson2ObjectMapperBuilderCustomizer
    // * @return org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
    // */
    //@Bean
    //public Jackson2ObjectMapperBuilderCustomizer customizer() {
    //    return builder -> {
    //        builder.locale(Locale.CHINA);
    //        builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
    //        builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //
    //        JavaTimeModule javaTimeModule = new JavaTimeModule();
    //        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    //        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    //        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
    //        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    //        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    //        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
    //
    //        builder.modules(javaTimeModule);
    //    };
    //}
    /**
     * 2、配置ObjectMapper
     * @return com.fasterxml.jackson.databind.ObjectMapper
     */
    @Bean
    public ObjectMapper initObjectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();

        JavaTimeModule javaTimeModule=new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //localDateTime按照 "yyyy-MM-dd HH:mm:ss"的格式进行序列化、反序列化

        objectMapper.registerModule(javaTimeModule);

        return objectMapper;
    }
}

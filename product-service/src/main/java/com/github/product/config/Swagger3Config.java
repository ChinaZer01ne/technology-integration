package com.github.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Zer01ne
 * @since 2020/11/15 21:15
 */
@Configuration
@EnableOpenApi
public class Swagger3Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // Swagger开关
                //.enable(false)
                .select()
                // RequestHandlerSelectors限定扫描接口位置
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .apis(RequestHandlerSelectors.basePackage("com.github.product.controller"))
                // 过滤路径
                .paths(PathSelectors.any())
                .build()
                .groupName("peach");
    }

    @Bean
    public Docket createOtherGroup() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .build()
                .groupName("Others");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Peach的Swagger3接口文档")
                .description("Made By Zer01ne.")
                .contact(new Contact("Zer01ne", "https://github.com/ChinaZer01ne", "suprepasslion@gmail.com"))
                .version("1.0")
                .build();
    }
}

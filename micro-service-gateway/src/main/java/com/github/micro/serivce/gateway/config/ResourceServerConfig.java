package com.github.micro.serivce.gateway.config;

import com.github.micro.serivce.gateway.auth.AuthorizationManager;
import com.github.micro.serivce.gateway.auth.CustomizedAccessDeniedHandler;
import com.github.micro.serivce.gateway.auth.CustomizedAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author Zer01ne
 * @since 2020/11/27 20:09
 */
@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        //httpSecurity.oauth2ResourceServer().jwt()
        //        .jwtAuthenticationConverter(jwtAuthenticationConverter());
        httpSecurity.authorizeExchange()
                // 白名单
                .pathMatchers("/auth/**").permitAll()
                //.pathMatchers("/user/**").permitAll()
                // swagger相关
                .pathMatchers("/swagger-ui/**").permitAll()
                .pathMatchers("/swagger-resources/**").permitAll()
                .pathMatchers("/v3/**").permitAll()
                // 鉴权管理器配置
                .anyExchange().access(new AuthorizationManager())
                .and().exceptionHandling()
                // 处理未授权
                .accessDeniedHandler(new CustomizedAccessDeniedHandler())
                //.accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.INTERNAL_SERVER_ERROR))
                //.accessDeniedHandler(new ServerWebExchangeDelegatingServerAccessDeniedHandler(new ServerWebExchangeDelegatingServerAccessDeniedHandler.DelegateEntry()))
                // 处理匿名访问
                .authenticationEntryPoint(new CustomizedAuthenticationEntryPoint())
                //.authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("http://www.baidu.com"))
                //.authenticationEntryPoint(new HttpBasicServerAuthenticationEntryPoint())
                //.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.OK))
                //.authenticationEntryPoint(new DelegatingServerAuthenticationEntryPoint(new DelegatingServerAuthenticationEntryPoint.DelegateEntry()))
                .and().csrf().disable();
        return httpSecurity.build();

    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("xxx");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("xxx");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}

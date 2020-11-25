package com.github.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Web安全配置
 * @author peach
 * @since 2020/11/25 10:19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        jdbcTemplate.query("select * from t_user",resultSet -> {
            System.out.println(resultSet.getString("user_name"));
            System.out.println(resultSet.getString("password"));
        });
        // TODO 从数据库获取用户信息
        auth
            // enable in memory based authentication with a user named
            // "user" and "admin"
            .inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("user")).roles("USER").and()
            .withUser("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN");
    }


}
package com.github.auth.server.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationServerConfigurationTest {

    @Test
    public void test(){
        System.out.println(new BCryptPasswordEncoder().encode("secret1"));
    }
}
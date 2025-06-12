package com.crombucket.storagemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.cromxt.authentication.JwtService;
import com.cromxt.authentication.JwtServiceImpl;

@Configuration
public class ApplicationConfig {
  
  @Bean
  JwtService jwtService(Environment environment){
    String secret = environment.getProperty("JWT_SECRET", String.class);
    Long expiration = environment.getProperty("JWT_EXPIRATION",Long.class);

    assert secret != null && expiration != null;
    return new JwtServiceImpl(secret,expiration); 
  }
}

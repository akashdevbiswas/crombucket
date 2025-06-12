package com.crombucket.storagemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cromxt.authentication.JwtService;
import com.cromxt.authentication.servlet.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

  private final JwtService jwtService;


  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, Environment environment) throws Exception{

    String location = environment.getProperty("TOKEN_EXPIRATION_REDIRECTION_URL",String.class);

    assert location != null;

    JwtAuthenticationFilter jwtfilter = new JwtAuthenticationFilter(jwtService,location);

    return httpSecurity
    .csrf(CsrfConfigurer::disable)
    .cors(CorsConfigurer::disable)
    .httpBasic(HttpBasicConfigurer::disable)
    .formLogin(FormLoginConfigurer::disable)
    .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
    .build();
  }

  @Bean
  UserDetailsService userDetailsService(){
    return username->{
      return null;
    };
  }

}

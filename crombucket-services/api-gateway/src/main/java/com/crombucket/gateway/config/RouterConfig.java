package com.crombucket.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

  private final RouteSpecifications routeSpecifications;
  
  @Bean
  RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
    return routeLocatorBuilder.routes()
    .route("user-service", routeSpecifications.USER_ROUTE)
    .route("storage-manager", routeSpecifications.STORAGE_MANAGER_ROUTE)
    .build();
  }
}

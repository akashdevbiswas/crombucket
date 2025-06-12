package com.crombucket.gateway.config;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.stereotype.Service;


@Service
public class RouteSpecifications {

   public final Function<PredicateSpec, Buildable<Route>> USER_ROUTE= r-> r.path("/user-service/**")
    .filters(fil->fil.rewritePath("/user-service/?(?<segment>.*)", "/${segment}"))
    .uri("http://localhost:8900");

    public final Function<PredicateSpec, Buildable<Route>> STORAGE_MANAGER_ROUTE= r-> r.path("/storage-manager/**")
    .filters(fil->fil.rewritePath("/storage-manager/?(?<segment>.*)", "/${segment}"))
    .uri("http://localhost:8903");

}

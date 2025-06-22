package com.cromxt.crombucket.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RequestPredicates.pathExtension;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ApplicationCofig {
  @Bean
  RouterFunction<ServerResponse> spaRouter() {
    ClassPathResource indexHtml = new ClassPathResource("static/index.html");

    List<String> staticExtensions = List.of("js", "css", "ico", "png", "jpg", "svg", "woff2");

    // This line ensure that all the requests /api/** or /static/*.{js,css,ico,png,jpg,svg,woff2} are ignored by this route.
    RequestPredicate isNotApiOrStatic = path("/api/**")
        .or(pathExtension(staticExtensions::contains)).negate();

    return route(isNotApiOrStatic, request -> ServerResponse.ok().bodyValue(indexHtml));
  }
}

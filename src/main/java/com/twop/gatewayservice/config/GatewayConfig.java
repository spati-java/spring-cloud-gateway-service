package com.twop.gatewayservice.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/get")
                            .filters(f -> f.addRequestHeader("hello", "world"))
                            .uri("http://httpbin.org:80"))

                .route(p-> p.path("/greet")
                            .filters(f->f.addRequestHeader("service" , "rating"))
                           .uri("http://localhost:8084")
                ).build();

    }

}

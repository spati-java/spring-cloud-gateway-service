package com.twop.gatewayservice.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableHystrix
@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/get")
                            .filters(f -> f.addRequestHeader("hello", "world"))
                            .uri("http://httpbin.org:80"))

                .route(p-> p.path("/greet")
                            .filters(f->f.addRequestHeader("service" , "k8s-demo")
                            .hystrix(config -> config.setName("k8s-demo-service"))
                            )
                           .uri("http://k8s-demo:8084")
                ).build();

    }

}

package com.jpersou.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    // TODO: 26/11/2023 Download dependência -> Gateway
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("user_route", r -> r.path("/user/**")
//                        .uri("http://localhost:8080"))
//                .route("product_route", r -> r.path("/product/**")
//                        .uri("http://localhost:8081"))
//                .route("shopping_route", r -> r.path("/shopping/**")
//                        .uri("http://localhost:8082"))
//                .build();
//
//    }
}

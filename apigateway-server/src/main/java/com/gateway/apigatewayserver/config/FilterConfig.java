package com.gateway.apigatewayserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


/*
@Configuration 을 이용해 설정 진행
RouteLocator Bean 생성
람다함수 이용
필터체이닝을 이용해 여러개의 필터를 한번에 등록할 수 있다
route와 header을 설정해주었다
Spring Cloud Gateway filter은 Gateway Handler Mapping 시 requestHeader을 추가해주고
전체 과정이 끝난 후 Gateway Handler Mapping에서 client로 response 할 때 responseHeader을 추가해줌
 */

@Configuration
public class FilterConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("first-service",r -> r.path("/first-service/**") // 아이디값부터 path, fillters 적용..
                        .filters(f->f.addRequestHeader("first-request","first-request-header")
                                .addResponseHeader("first-response", "first-response-header"))
                        .uri("http://localhost:8081")) //route 객체에 path 정보 추가



                .route(r -> r.path("second-service","/second-service/**")
                        .filters(f->f.addRequestHeader("second-request","second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8082"))
                .build();

    }
}

package com.gateway.apigatewayserver.config;

import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private CustomFilter customFilter;


    @Autowired
    private LoggingFilter loggingFilter;

/*
호출 우선순위는

글로벌 pre → 커스텀 pre → 커스텀 post → 글로벌 post

순으로 마치 스택처럼 호출 및 종료가 이뤄집니다.

 Custom global pre-filter executed
커스텀 프리 필터---af075d5f-1
커스텀 포스트필터 상태코드---200 OK
Custom global post-filter executed




그러나 만약 글로벌 필터(GlobalFIlter)가 있다면,
그것은 모든 요청에 대해 가장 먼저 실행되고 마지막으로 종료됩니다.
 따라서 "글로벌 프리 → 커스텀 프리 → 로깅 프리 → 로깅 포스트 → 커스텀 포스트 → 글로벌 포스트"
 순으로 동작하게 됩니다.


 */


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("first-service",r -> r.path("/first-service/**") // 아이디값부터 path, fillters 적용..
                        .filters(f->f.addRequestHeader("first-request","first-request-header")
                                .addResponseHeader("first-response", "first-response-header")
                                .filter(customFilter.apply(new CustomFilter.Config()))
                                .filter(loggingFilter.apply(LoggingFilter.Config.builder()
                                        .baseMessage("First Service Message")
                                        .preLogger(true)
                                        .postLogger(true)
                                        .build())))
                        .uri("lb://FIRST-SERVICE")) //route 객체에 path 정보 추가



                .route(r -> r.path("second-service","/second-service/**")
                        .filters(f->f.addRequestHeader("second-request","second-request-header")
                                .addResponseHeader("second-response", "second-response-header")
                                .filter(customFilter.apply(new CustomFilter.Config()))
                                .filter(loggingFilter.apply(LoggingFilter.Config.builder()
                                        .baseMessage("Second Service Message")
                                        .preLogger(true)
                                        .postLogger(true)
                                        .build())))
                        .uri("lb://SECOND-SERVICE"))
                .build();

    }
}

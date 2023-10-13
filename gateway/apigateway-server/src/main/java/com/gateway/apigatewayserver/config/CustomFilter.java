package com.gateway.apigatewayserver.config;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest; //webflux
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.ObjectInputFilter;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            System.out.println("커스텀 프리 필터---" + request.getId());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("커스텀 포스트필터 상태코드---" + response.getStatusCode());
            }));

        });
    }
    /*
    console
커스텀 프리 필터---8ec4452c-1  =========== HTTP 요청의 ID입니다.
커스텀 포스트필터 상태코드---200 OK   =========== HTTP 응답의 상태 코드

추가로 필요한 기능이나 수정 사항이 있다면 해당 부분을 커스텀 필터에 추가하거나 변경할 수 있습니다. 예를 들어 특정 조건에 따라 요청을 거부하거나, 추가적인 정보를 로깅하는 등의 기능을 구현할 수 있습니다.
     */
    public static  class  Config{

    }
}

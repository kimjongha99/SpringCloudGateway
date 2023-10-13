package com.gateway.apigatewayserver.config;


/*
Custom Filter이 router 하나하나에 직접 적용해야 했던 것이라면 Global Filter은 전체 router에 적용
Global Filter은 가장 먼저 실행되고 가장 마지막에 종료된다
 */


import org.springframework.core.Ordered;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Custom global pre-filter executed");

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println("Custom global post-filter executed");
        }));
    }

    @Override
    public int getOrder() {
        return -1;  // the lower the value, the higher the precedence
    }

    }

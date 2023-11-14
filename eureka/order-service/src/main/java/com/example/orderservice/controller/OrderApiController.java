package com.example.orderservice.controller;


import com.example.orderservice.dto.RequestOrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor // final 한에서 자동주입
@RequestMapping("/order-service")


public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<RequestOrderDto> createOrder(@RequestBody RequestOrderDto requestOrderDto) {
        RequestOrderDto createdOrder = orderService.createOrder(requestOrderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/orders/{userId}")
    public  ResponseEntity<?> getOrderListByUserId(@PathVariable String userId){
        List<Order> orders =orderService.getOrderListByUserId(userId)
                .orElseThrow(() -> new RuntimeException("없는아이디를 조회하셨습니다."))
                .stream().toList();

        return  ResponseEntity.ok(orders);
    }
}

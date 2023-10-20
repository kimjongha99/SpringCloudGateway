package com.example.orderservice.controller;


import com.example.orderservice.dto.RequestOrderDto;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor // final 한에서 자동주입
@RequestMapping("/order-service")


public class OrderApiController {

    private  final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<RequestOrderDto> createOrder(@RequestBody RequestOrderDto requestOrderDto) {
        RequestOrderDto createdOrder = orderService.createOrder(requestOrderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}

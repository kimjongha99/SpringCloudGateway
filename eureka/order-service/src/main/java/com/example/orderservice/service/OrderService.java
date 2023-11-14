package com.example.orderservice.service;

import com.example.orderservice.dto.RequestOrderDto;
import com.example.orderservice.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    RequestOrderDto createOrder(RequestOrderDto requestOrderDto);

    // userId 입력하면 Order 리스트 넘겨주는 메서드
    Optional<List<Order>> getOrderListByUserId(String userId);

    // userId 입력하면 Order 리스트 넘겨주는 메서드

}

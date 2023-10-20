package com.example.orderservice.service;

import com.example.orderservice.dto.RequestOrderDto;

public interface OrderService {
    RequestOrderDto createOrder(RequestOrderDto requestOrderDto);
}

package com.example.orderservice.service;

import com.example.orderservice.dto.RequestOrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements  OrderService{

    private  final OrderRepository orderRepository ;


    @Override
    public RequestOrderDto createOrder(RequestOrderDto requestOrderDto) {
        // DTO를 엔터티로 변환
        Order orderToSave = requestOrderDto.toEntity();

        // 엔터티 저장
        Order savedOrder = orderRepository.save(orderToSave);

        return RequestOrderDto.builder()
                .orderId(savedOrder.getOrderId())
                .userId(savedOrder.getUserId())
                .productId(savedOrder.getProductId())
                .count(savedOrder.getCount())
                .build();
    }
}

package com.example.orderservice.service;

import com.example.orderservice.dto.RequestOrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // userId 입력하면 Order 리스트 넘겨주는 메서드
    @Override
    public  Optional<List<Order>> getOrderListByUserId(String userId){
        return orderRepository.findOrderByUserId(userId);
    }

}

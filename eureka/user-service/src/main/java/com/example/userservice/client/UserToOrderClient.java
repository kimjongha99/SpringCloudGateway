package com.example.userservice.client;

import com.example.userservice.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE",path = "order-service")
public interface UserToOrderClient {

    @GetMapping("/orders/{userId}")
    ResponseEntity<List<Order>> getOrderListByUserId(@PathVariable String userId);



}

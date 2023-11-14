package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    // 쿼리메서드로 userId를 해당 유저의 이용해 전체 order 목록을 가져오는 메서드를 작성하세요.
      Optional<List<Order>>findOrderByUserId(String userId);


}

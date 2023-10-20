package com.example.orderservice.entity;


import com.example.orderservice.dto.RequestOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Builder  // Add this line.
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id" , updatable = false)
    private String orderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id")
    private String productId;

    private Long count;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


}

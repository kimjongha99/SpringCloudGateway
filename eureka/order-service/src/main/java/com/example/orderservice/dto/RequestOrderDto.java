package com.example.orderservice.dto;


import com.example.orderservice.entity.Order;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestOrderDto {

    private String orderId;
    private String userId;
    private String productId;
    private Long count;




    public Order toEntity(){
        return Order.builder()
                .orderId(UUID.randomUUID().toString())
                   .orderId(this.orderId)
                .count(this.count)
                .createdAt(LocalDateTime.now())
                 .userId(this.userId)
              .productId(this.productId)
                .build();
    }
}

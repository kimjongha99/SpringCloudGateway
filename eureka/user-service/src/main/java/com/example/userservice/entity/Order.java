package com.example.userservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

        private Long id;


        private String orderId;


        private String userId;


        private String productId;

        private Long count;

        private LocalDateTime createdAt;


}

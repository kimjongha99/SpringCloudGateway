package com.example.item_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RequestItemRegisterDto {
    private String productId;

    private String productName;

    private Long stock;

    private Long pricePerItem;

}

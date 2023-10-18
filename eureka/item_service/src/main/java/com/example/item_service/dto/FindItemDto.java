package com.example.item_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindItemDto {
    String productId;
    private String productName;
    private Long stock;
    private Long pricePerItem;
}

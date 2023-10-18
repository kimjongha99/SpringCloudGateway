package com.example.item_service.service;

import com.example.item_service.dto.FindItemDto;
import com.example.item_service.dto.RequestItemRegisterDto;
import com.example.item_service.entity.Item;
import com.example.item_service.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemDetailService implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public RequestItemRegisterDto createItem(RequestItemRegisterDto requestItemRegisterDto) {
        Item item = Item.builder()
                .productId(requestItemRegisterDto.getProductId())
                .productName(requestItemRegisterDto.getProductName())
                .stock(requestItemRegisterDto.getStock())
                .pricePerItem(requestItemRegisterDto.getPricePerItem())
                .build();

        itemRepository.save(item);

        return requestItemRegisterDto;
    }


    @Override
    public FindItemDto getItemByProductId(String productId) {
        // Find the item by productId in the database
        Item item = itemRepository.findByProductId(productId);

        // Convert Entity to DTO and return it
        return FindItemDto.builder()
                .productId(item.getProductId())
                .productName(item.getProductName())
                .stock(item.getStock())
                .pricePerItem(item.getPricePerItem())  // Corrected here
                .build();
    }
}
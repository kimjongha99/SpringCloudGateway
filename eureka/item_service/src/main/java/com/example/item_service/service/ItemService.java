package com.example.item_service.service;

import com.example.item_service.dto.FindItemDto;
import com.example.item_service.dto.RequestItemRegisterDto;

public interface ItemService {

    //    RequestCreateUserDto createUser(RequestCreateUserDto userDto);
     RequestItemRegisterDto createItem(RequestItemRegisterDto requestItemRegisterDto);

    FindItemDto getItemByProductId(String productId);
}

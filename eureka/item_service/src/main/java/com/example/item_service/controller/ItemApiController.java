package com.example.item_service.controller;

import com.example.item_service.dto.FindItemDto;
import com.example.item_service.dto.RequestItemRegisterDto;
import com.example.item_service.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item-service")
public class ItemApiController {
    private final Environment env;
    private  final  ItemService itemService;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User Service "
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration-time"));
    }

    @PostMapping("/items")  // Make sure to start your mapping with '/'.
    public ResponseEntity<RequestItemRegisterDto> createItem( @RequestBody RequestItemRegisterDto  requestItemRegisterDto) {
        RequestItemRegisterDto createItem = itemService.createItem(requestItemRegisterDto);

        return new ResponseEntity<>(createItem, HttpStatus.CREATED);
    }

    @GetMapping("/items/{productId}")
    public ResponseEntity<FindItemDto> getItemByProductId(@PathVariable String productId) {
        FindItemDto findItemDto = itemService.getItemByProductId(productId);

        if(findItemDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(findItemDto, HttpStatus.OK);
    }
}

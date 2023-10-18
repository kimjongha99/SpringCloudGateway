package com.example.item_service.repository;

import com.example.item_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByProductId(String productId);

}
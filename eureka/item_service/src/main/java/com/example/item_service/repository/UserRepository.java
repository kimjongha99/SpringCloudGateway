package com.example.item_service.repository;

import com.example.item_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Item, Long> {
}
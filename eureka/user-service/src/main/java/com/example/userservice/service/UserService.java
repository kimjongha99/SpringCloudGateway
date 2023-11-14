package com.example.userservice.service;


import com.example.userservice.dto.RequestCreateUserDto;
import com.example.userservice.dto.ResponseFindUserDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    RequestCreateUserDto createUser(RequestCreateUserDto userDto);

    ResponseFindUserDto findUserByUuid(String uuid);


    List<User> getAllUsers();

    ResponseFindUserDto getUserOrders(String userId);
}

package com.example.userservice.controller;


import com.example.userservice.client.UserToOrderClient;
import com.example.userservice.dto.RequestCreateUserDto;
import com.example.userservice.dto.ResponseFindUserDto;
import com.example.userservice.entity.Order;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserApiController {

    private final Environment env;
    private final UserService userService;  // Inject the UserService here.

    private  final UserToOrderClient userToOrderClient;
    private  final UserRepository userRepository;

    public UserApiController(Environment env, UserService userService, UserToOrderClient userToOrderClient, UserRepository userRepository) {
        this.env = env;
        this.userService = userService;
        this.userToOrderClient = userToOrderClient;
        this.userRepository = userRepository;
    }

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User Service "
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration-time"));
    }

    @PostMapping("/users")  // Make sure to start your mapping with '/'.
    public ResponseEntity<RequestCreateUserDto> createUser(@Valid @RequestBody RequestCreateUserDto requestCreateUserDto) {
        RequestCreateUserDto createdUser = userService.createUser(requestCreateUserDto);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("users/{uuid}")
    public ResponseFindUserDto findUserByUuid(@PathVariable String uuid) {
        return userService.findUserByUuid(uuid);
    }


    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<ResponseFindUserDto> getUserOrders(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserOrders(userId));
    }

}



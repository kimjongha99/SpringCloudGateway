package com.example.userservice.service;

import com.example.userservice.client.UserToOrderClient;
import com.example.userservice.dto.RequestCreateUserDto;
import com.example.userservice.dto.ResponseFindUserDto;
import com.example.userservice.entity.Order;
import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserDetailsService implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserToOrderClient userToOrderClient;

    @Override
    public RequestCreateUserDto createUser(RequestCreateUserDto userDto) {
        // Generate a random UUID for the user id and password.
        String randomUUID = UUID.randomUUID().toString();

        // TODO: Add logic to encrypt the password before storing it.
        // Never store plain-text passwords in your database!
        User user = User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .encPw(passwordEncoder.encode(userDto.getPw()))  // Encrypt the password here.
                .userId(userDto.getUserId())
                .uuid(randomUUID)  // Set the randomly generated UUID here.
                .build();

        userRepository.save(user);

        return userDto;
    }

    @Override
    public ResponseFindUserDto findUserByUuid(String uuid) {
        User user = userRepository.findUserByUuid(uuid);
        if (user == null) {
            throw new UserNotFoundException("해당유저가없습니다.");
        }
        return ResponseFindUserDto.of(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public ResponseFindUserDto getUserOrders(String userId) {
        // 1. 유저 조회
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found with userId: " + userId);
        }

        // 2. 주문 내역 조회
        ResponseEntity<List<Order>> orderResponse = userToOrderClient.getOrderListByUserId(userId);
        if (orderResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to fetch orders for userId: " + userId);
        }

        // 3. 응답 구성
        ResponseFindUserDto responseDto = new ResponseFindUserDto();
        responseDto.setUser(user);
        responseDto.setOrders(orderResponse.getBody());

        return responseDto;

    }
    }
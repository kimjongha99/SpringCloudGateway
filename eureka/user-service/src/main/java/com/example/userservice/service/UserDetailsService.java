package com.example.userservice.service;

import com.example.userservice.dto.RequestCreateUserDto;
import com.example.userservice.dto.ResponseFindUserDto;
import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserDetailsService implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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
         if(user==null){
             throw new UserNotFoundException("해당유저가없습니다.");
         }
       return ResponseFindUserDto.of(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}

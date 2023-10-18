package com.example.userservice.dto;

import com.example.userservice.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponseFindUserDto {

    private String uuid;

    private Long id;

    private String email;

    private String name;

    private String userId;


    public static ResponseFindUserDto of(User user) {
        return ResponseFindUserDto.builder()
                .uuid(user.getUuid())
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .build();
    }
}

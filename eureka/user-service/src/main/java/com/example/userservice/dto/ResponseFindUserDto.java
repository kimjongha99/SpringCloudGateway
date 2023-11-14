package com.example.userservice.dto;

import com.example.userservice.entity.Order;
import com.example.userservice.entity.User;
import lombok.*;


import java.util.List;

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

    private List<Order> orders; // 주문 목록 필드 추가

    public static ResponseFindUserDto of(User user) {
        return ResponseFindUserDto.builder()
                .uuid(user.getUuid())
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .build();
    }

    public void setUser(User user) {
        this.uuid = user.getUuid();
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.userId = user.getUserId();
    }

}

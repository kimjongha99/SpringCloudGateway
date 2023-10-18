package com.example.userservice.dto;

import lombok.*;
import org.hibernate.annotations.CollectionId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestCreateUserDto {

    @Email
    private String email;

    @Size(min = 8, max = 20, message = "비밀번호는 최소 8글자이고, 최대 20글자입니다.")
    private String pw;

    @NotNull
    private String name;

    private  String userId;

}
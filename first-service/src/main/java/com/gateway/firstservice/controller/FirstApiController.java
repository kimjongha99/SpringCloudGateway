package com.gateway.firstservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
public class FirstApiController {
    @GetMapping("/hello")
    public String hello(){
        return "<h1> hello first - service~ </h1>";
    }

    @GetMapping("/header")  // 헤더 메세지를 가져올수있다.
    public String header(@RequestHeader("first-request") String header){
        return "헤더 메세지 : " + header;
    }



}

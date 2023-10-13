package com.gateway.secondservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
public class SecondApiController {
    @GetMapping("/hello")
    public String hello(){
        return "<h1> hello second - service~ </h1>";
    }
}
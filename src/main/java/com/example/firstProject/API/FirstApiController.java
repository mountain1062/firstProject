package com.example.firstProject.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // REST CONTROLLER는 JSON반환
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello";
    }
}

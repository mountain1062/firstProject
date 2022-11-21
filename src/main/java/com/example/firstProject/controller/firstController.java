package com.example.firstProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 일반컨트롤러는 HTML 소스를 반환?
public class firstController {

    @GetMapping("/hi")
    public String HIHI(Model model){
        model.addAttribute("username","DUSAN");
        return "greetings";
    }

    @GetMapping("/bye")
    public String BYBY(Model model){
        model.addAttribute("nickname","두산");
        return "Goodbye";
    }
}

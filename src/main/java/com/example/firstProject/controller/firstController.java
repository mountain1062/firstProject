package com.example.firstProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
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

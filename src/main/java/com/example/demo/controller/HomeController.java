package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  //localhost:8080으로 들어오면 이게 호출됨
    public String home(){
        return "home"; //home.html 출력됨
    }
}

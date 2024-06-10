package com.shop.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContoller {

    @GetMapping("/")
    public String main(){
        return "main";
    }
}

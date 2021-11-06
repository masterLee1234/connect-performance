package com.performance.connect.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class MainController {
    @GetMapping
    public String index(Model model){
        return "index";
    }
}

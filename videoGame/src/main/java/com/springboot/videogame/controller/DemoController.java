package com.springboot.videogame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showLanding() {

        return "landing";
    }

    @GetMapping("/home")
    public String showHome() {

        return "home";
    }

}










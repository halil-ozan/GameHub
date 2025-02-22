package com.springboot.videogame.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/info")
    public String getSessionInfo(HttpSession session) {
        return "Session ID: " + session.getId() + ", Created at: " + session.getCreationTime() ;
    }
}

package com.example.coursework.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage() {
        log.info("Showing login page");
        return "login";
    }
}

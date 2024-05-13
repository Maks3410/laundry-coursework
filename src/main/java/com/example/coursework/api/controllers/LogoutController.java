package com.example.coursework.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LogoutController {
    @GetMapping("/logout")
    public String showLogoutPage() {
        log.info("Logging out");
        return "redirect:/login?logout";
    }
}

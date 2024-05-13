package com.example.coursework.api.controllers;

import com.example.coursework.api.services.UserService;
import com.example.coursework.store.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@AllArgsConstructor
public class RegistrationController {
    private UserService userService;

    @GetMapping("/registration")
    public String showRegistrationPage() {
        log.info("Showing registration page");
        return "registration";
    }

    @PostMapping("/registration")
    public String createUserWithForm(@ModelAttribute("username") String username,
                          @ModelAttribute("email") String email,
                          @ModelAttribute("password") String password) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles("ROLE_USER");
        log.info("Trying to create user with registration form");
        userService.createUser(user);
        return "redirect:/login";
    }

    @PostMapping("/users/new")
    public String createUserWithUserEntity(@RequestBody UserEntity user) {
        log.info("Force creating user with user entity");
        return userService.createUser(user);
    }
}

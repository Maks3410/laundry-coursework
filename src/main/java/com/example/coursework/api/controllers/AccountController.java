package com.example.coursework.api.controllers;

import com.example.coursework.api.services.UserService;
import com.example.coursework.store.entities.OrderEntity;
import com.example.coursework.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class AccountController {
    UserRepository userRepository;
    UserService userService;

    @GetMapping("/account")
    public String showAccountPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String email = userRepository.findByUsername(username).get().getEmail();
        log.info("Showing account page");
        model.addAttribute("username", username);
        model.addAttribute("email", email);

        List<OrderEntity> orders = userRepository.findByUsername(username).get().getOrders();

        model.addAttribute("orders", orders);
        return "account";
    }
}

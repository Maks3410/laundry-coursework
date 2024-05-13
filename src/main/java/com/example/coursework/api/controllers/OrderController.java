package com.example.coursework.api.controllers;

import com.example.coursework.api.dto.OrderDto;
import com.example.coursework.api.services.OrderService;
import com.example.coursework.store.entities.OrderEntity;
import com.example.coursework.store.entities.UserEntity;
import com.example.coursework.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@Slf4j
@Controller
public class OrderController {
    private OrderService orderService;
    private UserRepository userRepository;

    @GetMapping("/orders/new")
    public String showOrderForm(Model model) {
        log.info("Showing new order page");
        model.addAttribute("orderDto", new OrderDto());
        return "neworder";
    }

    @PostMapping("/orders/new")
    @Transactional
    public String createOrder(@ModelAttribute("orderDto") OrderDto orderDto) {
        log.info("create order called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        UserEntity user = userRepository.findByUsername(currentUsername).get();
        log.info("Found user" + user);

        OrderEntity order = new OrderEntity();
        order.setDate(orderDto.getDate());
        order.setDry(orderDto.getDry());
        order.setWeight(orderDto.getWeight());
        order.setPrice(orderDto.getPrice());

        log.info("Created order" + order);

        user.getOrders().add(order);
        orderService.createOrder(order);
        userRepository.save(user);

        log.info("order saved");

        return "redirect:/orders/new?success";
    }
}

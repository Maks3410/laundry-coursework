package com.example.coursework.api.controllers;

import com.example.coursework.api.services.UserService;
import com.example.coursework.store.entities.OrderEntity;
import com.example.coursework.store.entities.UserEntity;
import com.example.coursework.store.repositories.OrderRepository;
import com.example.coursework.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/user/orders")
    @ResponseBody
    public List<OrderEntity> getOrdersOfUser(@RequestParam String username) {
        return userService.getOrdersOfUser(username);
    }

    @GetMapping("/user/{username}")
    @ResponseBody
    public UserEntity getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/user/update-email")
    @ResponseBody
    public String updateUserEmail(@RequestParam String username, @RequestParam String newEmail) {
        boolean updated = userService.updateUserEmail(username, newEmail);
        if (updated) {
            return "Email updated successfully";
        } else {
            return "User not found with username: " + username;
        }
    }

    @DeleteMapping("/user/delete")
    @ResponseBody
    public String deleteUserByUsername(@RequestParam String username) {
        boolean deleted = userService.deleteUserByUsername(username);
        if (deleted) {
            return "User deleted successfully";
        } else {
            return "User not found with username: " + username;
        }
    }

}

package com.example.coursework.api.services;

import com.example.coursework.api.exceptions.BadRequestException;
import com.example.coursework.store.entities.OrderEntity;
import com.example.coursework.store.entities.UserEntity;
import com.example.coursework.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String createUser(UserEntity userEntity) {

        String username = userEntity.getUsername();
        String email = userEntity.getEmail();
        String password = userEntity.getPassword();

        if (username.trim().isEmpty()) {
            log.error("Username can't be empty");
            throw new BadRequestException("Username can't be empty");
        }

        userRepository
                .findByUsername(username)
                .ifPresent(user -> {
                    log.error("User with username " + username + " already exists");
                    throw new BadRequestException("User with username \"%s\" already exists");
                });

        userRepository
                .findByEmail(email)
                .ifPresent(user -> {
                    log.error("User with email " + email + " already exists");
                    throw new BadRequestException("User with email \"%s\" already exists");
                });

        userEntity.setPassword(passwordEncoder.encode(password));

        userRepository.save(userEntity);
        log.info("User " + username + " is saved");
        return "User is saved!";
    }

    public List<OrderEntity> getOrdersOfUser(String username) {
        UserEntity user = getUserByUsername(username);
        if (user != null) {
            return user.getOrders();
        }
        return null;
    }

    public UserEntity getUserByUsername(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }

    public boolean updateUserEmail(String username, String newEmail) {
        UserEntity user = getUserByUsername(username);
        if (user != null) {
            user.setEmail(newEmail);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUserByUsername(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            userRepository.delete(user);
            return true;
        }
        return false;
    }

}

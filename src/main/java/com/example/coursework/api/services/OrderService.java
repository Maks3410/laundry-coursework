package com.example.coursework.api.services;

import com.example.coursework.store.entities.OrderEntity;
import com.example.coursework.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {
    OrderRepository orderRepository;

    @Transactional
    public void createOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
        log.info("Order is saved");
    }
}

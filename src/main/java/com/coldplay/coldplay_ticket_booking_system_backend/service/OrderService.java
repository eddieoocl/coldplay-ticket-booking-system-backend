// src/main/java/com/coldplay/coldplay_ticket_booking_system_backend/service/TicketService.java

package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Order;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findByUserId(Integer userId) {
        return orderRepository.findByUserUserId(userId);
    }
}
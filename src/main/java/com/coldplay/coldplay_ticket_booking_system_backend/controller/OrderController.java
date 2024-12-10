package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Order;
import com.coldplay.coldplay_ticket_booking_system_backend.model.Ticket;
import com.coldplay.coldplay_ticket_booking_system_backend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/user/{userId}")
    public List<Order> getOrderByUserId(@PathVariable Integer userId) {
        return orderService.findByUserId(userId);
    }
}
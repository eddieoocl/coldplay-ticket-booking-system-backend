// src/main/java/com/coldplay/coldplay_ticket_booking_system_backend/controller/TicketController.java

package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Ticket;
import com.coldplay.coldplay_ticket_booking_system_backend.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/user/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable Integer userId) {
        return ticketService.getTicketsByUserId(userId);
    }
}
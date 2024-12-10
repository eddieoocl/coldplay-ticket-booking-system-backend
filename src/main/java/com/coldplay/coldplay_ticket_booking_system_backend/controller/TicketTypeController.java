package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.TicketType.TicketTypeResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.service.TicketTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket-types")
@AllArgsConstructor
public class TicketTypeController {
    private final TicketTypeService ticketTypeService;

    @GetMapping("/concert/{concertId}")
    public List<TicketTypeResponse> getTicketTypesByConcertId(@PathVariable int concertId) {
        return ticketTypeService.getTicketTypesByConcertId(concertId);
    }
}
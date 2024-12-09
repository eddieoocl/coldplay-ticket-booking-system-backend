package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Ticket;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<Ticket> getTicketsByUserId(Integer userId) {
        return ticketRepository.findByUserUserId(userId);
    }
}
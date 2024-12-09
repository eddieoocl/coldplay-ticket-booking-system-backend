// src/main/java/com/coldplay/coldplay_ticket_booking_system_backend/service/TicketService.java

package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.TicketDto;
import com.coldplay.coldplay_ticket_booking_system_backend.model.Ticket;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<TicketDto> getTicketsByUserId(Integer userId) {
        List<Ticket> tickets = ticketRepository.findByUserUserId(userId);
        return tickets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TicketDto convertToDto(Ticket ticket) {
        return new TicketDto(
                ticket.getTicketId(),
                ticket.getTicketNumber(),
                ticket.getUser().getUserId(),
                ticket.getUser().getFirstName(),
                ticket.getUser().getLastName(),
                ticket.getOrderTicket().getTicketType().getConcert().getConcertId(),
                ticket.getOrderTicket().getTicketType().getConcert().getName(),
                ticket.getOrderTicket().getTicketType().getConcert().getDate().toEpochSecond(java.time.ZoneOffset.UTC),
                ticket.getOrderTicket().getTicketType().getConcert().getVenue(),
                ticket.getOrderTicket().getTicketType().getTicketTypeId(),
                ticket.getOrderTicket().getTicketType().getTypeName(),
                ticket.getSeatNumber(),
                ticket.getOrderTicket().getPrice(),
                ticket.getStatus(),
                ticket.getIssueDate().toEpochSecond(java.time.ZoneOffset.UTC)
        );
    }
}
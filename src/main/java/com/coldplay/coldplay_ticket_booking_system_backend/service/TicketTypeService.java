package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.TicketType.TicketTypeResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.model.TicketType;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.TicketTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;

    public List<TicketTypeResponse> getTicketTypesByConcertId(int concertId) {
        List<TicketType> ticketTypes = ticketTypeRepository.findByConcert_ConcertId(concertId);
        return ticketTypes.stream()
                .map(ticketType -> new TicketTypeResponse(
                        ticketType.getTicketTypeId(),
                        ticketType.getTypeName(),
                        ticketType.getPrice()))
                .collect(Collectors.toList());
    }
}
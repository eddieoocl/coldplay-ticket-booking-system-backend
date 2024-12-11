package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.TicketType.TicketTypeResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.model.Merchandise;
import com.coldplay.coldplay_ticket_booking_system_backend.model.TicketType;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.MerchandiseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;

    public List<Merchandise> getAllByConcertId(int concertId) {
        return merchandiseRepository.findAllByConcert_ConcertId(concertId);
    }
}
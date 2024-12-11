package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.concert.ConcertDetailResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.dto.concert.ConcertResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.model.Merchandise;
import com.coldplay.coldplay_ticket_booking_system_backend.service.ConcertService;
import com.coldplay.coldplay_ticket_booking_system_backend.service.MerchandiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/merchandises")
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    @GetMapping("/{concertId}")
    public List<Merchandise> getAllByConcertId(@PathVariable int concertId) {
        return merchandiseService.getAllByConcertId(concertId);
    }
}
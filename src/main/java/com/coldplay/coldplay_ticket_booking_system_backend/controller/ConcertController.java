package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.concert.ConcertDetailResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.dto.concert.ConcertResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.service.ConcertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/concerts")
public class ConcertController {

    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping
    public List<ConcertResponse> getAllConcerts() {
        return concertService.getAllConcertResponses();
    }

    @GetMapping("/{concertId}")
    public ResponseEntity<ConcertDetailResponse> getConcertDetail(@PathVariable Integer concertId) {
        Optional<ConcertDetailResponse> concertDetail = concertService.getConcertDetailById(concertId);
        return concertDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
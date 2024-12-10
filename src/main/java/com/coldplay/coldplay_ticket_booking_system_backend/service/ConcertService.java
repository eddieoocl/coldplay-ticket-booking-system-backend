package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.concert.ConcertDetailResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.dto.concert.ConcertResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.model.Concert;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.ConcertRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ConcertService {

    private final ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }

    public List<ConcertResponse> getAllConcertResponses() {
        List<Concert> concerts = getAllConcerts();
        return concerts.stream().map(this::convertToDto).toList();
    }

    public Optional<ConcertDetailResponse> getConcertDetailById(Integer concertId) {
        Optional<Concert> concert = concertRepository.findById(concertId);
        return concert.map(this::convertToDetailDto);
    }

    private ConcertResponse convertToDto(Concert concert) {
        ConcertResponse dto = new ConcertResponse();
        dto.setConcertId(concert.getConcertId());
        dto.setDate(concert.getDate().format(DateTimeFormatter.ofPattern("MMMM d", Locale.ENGLISH)));
        dto.setYear(String.valueOf(concert.getDate().getYear()));
        dto.setVenue(concert.getVenue());
        dto.setLocation(concert.getCity() + ", " + concert.getCountry());
        dto.setStatus("SOLD OUT");
        return dto;
    }

    private ConcertDetailResponse convertToDetailDto(Concert concert) {
        ConcertDetailResponse dto = new ConcertDetailResponse();
        dto.setConcertId(concert.getConcertId());
        dto.setName(concert.getName());
        dto.setDate(concert.getDate().format(DateTimeFormatter.ofPattern("MMMM d", Locale.ENGLISH)));
        dto.setYear(String.valueOf(concert.getDate().getYear()));
        dto.setVenue(concert.getVenue());
        dto.setLocation(concert.getCity() + ", " + concert.getCountry());
        dto.setDescription(concert.getDescription());
        dto.setImageUrl(concert.getImageUrl());
        dto.setTicketSaleStart(concert.getTicketSaleStart().format(DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.ENGLISH)));
        dto.setTicketSaleEnd(concert.getTicketSaleEnd().format(DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.ENGLISH)));
        dto.setTotalSeats(concert.getTotalSeats());
        dto.setStatus("SOLD OUT");
        return dto;
    }
}
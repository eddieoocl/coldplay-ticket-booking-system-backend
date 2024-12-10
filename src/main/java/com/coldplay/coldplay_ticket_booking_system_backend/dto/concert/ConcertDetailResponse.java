package com.coldplay.coldplay_ticket_booking_system_backend.dto.concert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcertDetailResponse {
    private Integer concertId;
    private String name;
    private String date;
    private String year;
    private String venue;
    private String location;
    private String description;
    private String imageUrl;
    private String ticketSaleStart;
    private String ticketSaleEnd;
    private int totalSeats;
    private String status;
}
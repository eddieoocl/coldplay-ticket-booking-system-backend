package com.coldplay.coldplay_ticket_booking_system_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Integer ticketId;
    private String ticketNumber;
    private Integer userId;
    private String firstname;
    private String lastname;
    private Integer concertId;
    private String concertName;
    private Long concertDate;
    private String venue;
    private Integer ticketTypeId;
    private String typeName;
    private String seatNumber;
    private Double price;
    private String status;
    private Long issueDate;
}
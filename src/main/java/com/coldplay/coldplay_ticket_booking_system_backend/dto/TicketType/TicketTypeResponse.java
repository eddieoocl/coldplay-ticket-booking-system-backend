package com.coldplay.coldplay_ticket_booking_system_backend.dto.TicketType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeResponse {
    private Integer id;
    private String name;
    private double price;
}
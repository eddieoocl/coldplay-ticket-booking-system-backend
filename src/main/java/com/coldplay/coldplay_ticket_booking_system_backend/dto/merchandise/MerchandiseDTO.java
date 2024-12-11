package com.coldplay.coldplay_ticket_booking_system_backend.dto.merchandise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchandiseDTO {
    private Integer id;
    private String name;
    private Double price;
    private String image;
    private Boolean isCharity;
}
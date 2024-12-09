package com.coldplay.coldplay_ticket_booking_system_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Ticket_Types")
@AllArgsConstructor
@NoArgsConstructor
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketTypeId;

    @ManyToOne
    @JoinColumn(name = "concert_id")
    private Concert concert;

    @Column(nullable = false)
    private String typeName;

    @Column(nullable = false)
    private Double price;
}
package com.coldplay.coldplay_ticket_booking_system_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Ticket_Inventory")
@AllArgsConstructor
@NoArgsConstructor
public class TicketInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketInventoryId;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;

    @Column(nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false)
    private Integer soldQuantity;
}
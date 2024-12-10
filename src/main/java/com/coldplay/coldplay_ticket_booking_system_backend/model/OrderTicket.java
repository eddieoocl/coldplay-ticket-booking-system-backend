package com.coldplay.coldplay_ticket_booking_system_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Order_Tickets")
@AllArgsConstructor
@NoArgsConstructor
public class OrderTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderTicketId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    private String moviegoer;

}
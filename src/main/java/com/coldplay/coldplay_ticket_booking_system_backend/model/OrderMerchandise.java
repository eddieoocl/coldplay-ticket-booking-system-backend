package com.coldplay.coldplay_ticket_booking_system_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Order_Merchandise")
@AllArgsConstructor
@NoArgsConstructor
public class OrderMerchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderMerchandiseId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "merchandise_id")
    private Merchandise merchandise;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;
}
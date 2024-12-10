package com.coldplay.coldplay_ticket_booking_system_backend.model;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonFormat;
>>>>>>> View_&_Select_Concert_List
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Concerts")
@AllArgsConstructor
@NoArgsConstructor
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer concertId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    @Column(nullable = false)
    private String venue;

    private String city;

    private String country;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    private LocalDateTime ticketSaleStart;

    private LocalDateTime ticketSaleEnd;

    @Column(nullable = false)
    private Integer totalSeats;
=======
    private String country;
    private String description;
    private String imageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime ticketSaleStart;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime ticketSaleEnd;

    @Column(nullable = false)
    private int totalSeats;
>>>>>>> View_&_Select_Concert_List
}
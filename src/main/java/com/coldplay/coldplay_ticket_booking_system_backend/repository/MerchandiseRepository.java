package com.coldplay.coldplay_ticket_booking_system_backend.repository;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Merchandise;
import com.coldplay.coldplay_ticket_booking_system_backend.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Integer> {
    List<Merchandise> findAllByConcert_ConcertId(int concertId);
}
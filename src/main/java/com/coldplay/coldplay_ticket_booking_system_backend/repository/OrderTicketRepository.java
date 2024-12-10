package com.coldplay.coldplay_ticket_booking_system_backend.repository;

import com.coldplay.coldplay_ticket_booking_system_backend.model.OrderTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderTicketRepository extends JpaRepository<OrderTicket, Integer> {
    List<OrderTicket> findByOrder_OrderId(Integer orderId);
}
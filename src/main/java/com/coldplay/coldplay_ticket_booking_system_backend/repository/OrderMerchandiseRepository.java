package com.coldplay.coldplay_ticket_booking_system_backend.repository;

import com.coldplay.coldplay_ticket_booking_system_backend.model.OrderMerchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderMerchandiseRepository extends JpaRepository<OrderMerchandise, Integer> {
    List<OrderMerchandise> findByOrder_OrderId(Integer orderId);
}
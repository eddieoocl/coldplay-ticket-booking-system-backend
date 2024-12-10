package com.coldplay.coldplay_ticket_booking_system_backend.service;


import com.coldplay.coldplay_ticket_booking_system_backend.dto.order.OrderRequest;
import com.coldplay.coldplay_ticket_booking_system_backend.model.*;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final OrderTicketRepository orderTicketRepository;
    private final OrderMerchandiseRepository orderMerchandiseRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(0.0); // This will be updated later
        order.setStatus("PENDING");
        order.setPaymentMethod("UNPAID");
        orderRepository.save(order);

        double totalPrice = 0.0;

        for (OrderRequest.TicketInfo ticketInfo : orderRequest.getTicketInfo()) {
            TicketType ticketType = ticketTypeRepository.findById(ticketInfo.getTicketTypeId())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket type not found"));

            OrderTicket orderTicket = new OrderTicket();
            orderTicket.setOrder(order);
            orderTicket.setTicketType(ticketType);
            orderTicket.setQuantity(1); // Assuming 1 ticket per moviegoer
            orderTicket.setPrice(ticketType.getPrice());
            orderTicketRepository.save(orderTicket);

            totalPrice += ticketType.getPrice();
        }

        for (OrderRequest.MerchandiseInfo merchandiseInfo : orderRequest.getMerchandiseInfo()) {
            Merchandise merchandise = merchandiseRepository.findById(merchandiseInfo.getMerchandiseId())
                    .orElseThrow(() -> new IllegalArgumentException("Merchandise not found"));

            OrderMerchandise orderMerchandise = new OrderMerchandise();
            orderMerchandise.setOrder(order);
            orderMerchandise.setMerchandise(merchandise);
            orderMerchandise.setQuantity(merchandiseInfo.getCount());
            orderMerchandise.setPrice(merchandise.getPrice() * merchandiseInfo.getCount());
            orderMerchandiseRepository.save(orderMerchandise);

            totalPrice += merchandise.getPrice() * merchandiseInfo.getCount();
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        return order;
    }
}
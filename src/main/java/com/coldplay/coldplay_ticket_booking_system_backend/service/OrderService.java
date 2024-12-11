package com.coldplay.coldplay_ticket_booking_system_backend.service;


import com.coldplay.coldplay_ticket_booking_system_backend.dto.order.OrderRequest;
import com.coldplay.coldplay_ticket_booking_system_backend.dto.order.OrderResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.model.*;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final OrderTicketRepository orderTicketRepository;
    private final OrderMerchandiseRepository orderMerchandiseRepository;
    private final TicketRepository ticketRepository;


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
            orderTicket.setMoviegoer(ticketInfo.getMoviegoer());
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

    public OrderResponse getOrderResponseById(Integer orderId) {
        final OrderResponse orderResponse = new OrderResponse();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        orderResponse.setOrderId(orderId.toString());
        orderResponse.setTotalPrices(order.getTotalPrice().toString());
        orderResponse.setOrderTime(order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        orderResponse.setOrderStatus(order.getStatus());

        List<OrderTicket> orderTicket = orderTicketRepository.findByOrder_OrderId(orderId);
        List<OrderResponse.TicketInfo> ticketInfoList = orderTicket.stream().map(ticket -> {
            OrderResponse.TicketInfo ticketInfo = new OrderResponse.TicketInfo();
            ticketInfo.setId(ticket.getOrderTicketId());
            ticketInfo.setTicketType(ticket.getTicketType().getTypeName());
            ticketInfo.setMoviegoer(ticket.getMoviegoer());
            ticketInfo.setPrice(ticket.getPrice());

            Integer orderTicketId = ticket.getOrderTicketId();

            List<Ticket> tickets = ticketRepository.findByOrderTicketOrderTicketId(orderTicketId);
            ticketInfo.setTicketNumber(tickets.isEmpty() ? null : tickets.get(0).getTicketNumber());
            return ticketInfo;
        }).toList();
        orderResponse.setTicketInfo(ticketInfoList);

        List<OrderMerchandise> orderMerchandise = orderMerchandiseRepository.findByOrder_OrderId(orderId);
        List<OrderResponse.MerchandiseInfo> merchandiseInfoList = orderMerchandise.stream().map(merchandise -> {
            OrderResponse.MerchandiseInfo merchandiseInfo = new OrderResponse.MerchandiseInfo();
            merchandiseInfo.setMerchandiseId(merchandise.getMerchandise().getMerchandiseId());
            merchandiseInfo.setMerchandiseName(merchandise.getMerchandise().getName());
            merchandiseInfo.setPrice(merchandise.getPrice());
            merchandiseInfo.setCount(merchandise.getQuantity());
            return merchandiseInfo;
        }).toList();

        orderResponse.setMerchandiseInfo(merchandiseInfoList);

        return orderResponse;
    }
}
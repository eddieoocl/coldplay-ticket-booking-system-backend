package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Order;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    public boolean processPayment(Order order) {
        // Validate payment details (e.g., card number, expiry date, etc.)
        // This is a simplified example. In a real application, you would integrate with a payment gateway.

        if (isValidPaymentDetails(order)) {
            // Save order to the database
            orderRepository.save(order);
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidPaymentDetails(Order order) {
        // Implement validation logic here
        return true;
    }
}
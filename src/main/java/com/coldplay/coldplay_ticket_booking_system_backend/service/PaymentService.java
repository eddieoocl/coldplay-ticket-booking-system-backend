package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Payment;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public boolean processPayment(Payment payment) {
        // Validate payment details
        if (!validatePaymentDetails(payment)) {
            return false;
        }

        // Process payment (dummy implementation)
        // In a real-world scenario, integrate with a payment gateway

        // Save payment details to the database
        paymentRepository.save(payment);
        return true;
    }

    private boolean validatePaymentDetails(Payment payment) {
        // Add validation logic (e.g., card number format, expiration date)
        return payment.getCardNumber() != null && payment.getExpirationDate() != null && payment.getCvv() != null;
    }
}
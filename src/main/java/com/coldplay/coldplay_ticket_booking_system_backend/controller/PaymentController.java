package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Order;
import com.coldplay.coldplay_ticket_booking_system_backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitPayment(@RequestBody Order order) {
        boolean isPaymentSuccessful = paymentService.processPayment(order);
        if (isPaymentSuccessful) {
            return ResponseEntity.ok("Payment Successful!");
        } else {
            return ResponseEntity.status(400).body("Payment Failed!");
        }
    }
}
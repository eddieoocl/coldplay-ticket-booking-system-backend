package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Payment;
import com.coldplay.coldplay_ticket_booking_system_backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitPayment(@RequestBody Payment payment) {
        boolean isProcessed = paymentService.processPayment(payment);
        if (isProcessed) {
            return ResponseEntity.ok("Payment Successful!");
        } else {
            return ResponseEntity.status(400).body("Payment Failed!");
        }
    }
}
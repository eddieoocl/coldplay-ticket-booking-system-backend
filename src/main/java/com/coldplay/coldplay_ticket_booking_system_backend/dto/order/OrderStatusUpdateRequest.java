// src/main/java/com/coldplay/coldplay_ticket_booking_system_backend/dto/order/OrderStatusUpdateRequest.java

package com.coldplay.coldplay_ticket_booking_system_backend.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateRequest {
    private String status;
}
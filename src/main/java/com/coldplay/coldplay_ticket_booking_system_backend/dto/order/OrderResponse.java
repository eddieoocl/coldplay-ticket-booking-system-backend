package com.coldplay.coldplay_ticket_booking_system_backend.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String orderId;
    private String totalPrices;
    private String orderTime;
    private String orderStatus;
    private List<TicketInfo> ticketInfo;
    private List<MerchandiseInfo> merchandiseInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TicketInfo {
        private String ticketType;
        private String moviegoer;
        private String ticketNumber;
        private Double price;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MerchandiseInfo {
        private String merchandiseName;
        private Double price;
        private int count;
    }
}
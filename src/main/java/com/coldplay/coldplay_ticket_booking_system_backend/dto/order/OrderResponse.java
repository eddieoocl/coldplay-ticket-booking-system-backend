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
    private String concertId;
    private String orderTime;
    private String totalPrices;
    private String paymentMethod;
    private String paymentStatus;
    private List<TicketInfo> ticketInfo;
    private List<MerchandiseInfo> merchandiseInfo;
    private ConcertData concertData;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TicketInfo {
        private String id;
        private String ticketType;
        private String price;
        private String seat;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MerchandiseInfo {
        private String merchandiseId;
        private String merchandiseName;
        private Double price;
        private int count;
        private boolean isCharity;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConcertData {
        private String id;
        private String name;
        private String date;
        private String time;
        private String venue;
        private String address;
    }
}
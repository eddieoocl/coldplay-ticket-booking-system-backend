package com.coldplay.coldplay_ticket_booking_system_backend.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Integer userId;
    private List<TicketInfo> ticketInfo;
    private List<MerchandiseInfo> merchandiseInfo;

    @Data
    public static class TicketInfo {
        private Integer ticketTypeId;
        private Integer count;
    }

    @Data
    public static class MerchandiseInfo {
        private Integer merchandiseId;
        private Integer count;
    }
}
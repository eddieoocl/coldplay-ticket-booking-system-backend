package com.coldplay.coldplay_ticket_booking_system_backend.repository;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Test;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {
    public Test get(int id) {
        return new Test(id, "This is a message from the backend.");
    }

    public Test post(String name) {
        return new Test(2, name);
    }
}

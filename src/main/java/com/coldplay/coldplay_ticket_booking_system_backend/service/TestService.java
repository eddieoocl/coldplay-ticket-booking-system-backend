package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Test;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public Test get(int id) {
        return testRepository.get(id);
    }

    public Test post(String name) {
        return testRepository.post(name);
    }
}

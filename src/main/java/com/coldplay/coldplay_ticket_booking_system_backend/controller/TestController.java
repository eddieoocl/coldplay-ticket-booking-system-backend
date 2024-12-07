package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.TestPostRequestDto;
import com.coldplay.coldplay_ticket_booking_system_backend.model.Test;
import com.coldplay.coldplay_ticket_booking_system_backend.service.TestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    @GetMapping
    public Test get(@RequestParam Integer id) {
        return testService.get(id);
    }

    @PostMapping
    public Test post(@Valid @RequestBody TestPostRequestDto testPostRequestDto) {
        return testService.post(testPostRequestDto.getName());
    }
}
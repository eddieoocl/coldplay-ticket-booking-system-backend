package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Ticket;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.TicketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    @DisplayName("Should return tickets for a given user ID")
    void shouldReturnTicketsForGivenUserId() {
        // Given
        Integer userId = 1;
        List<Ticket> expectedTickets = List.of(new Ticket(), new Ticket());
        when(ticketRepository.findByUserUserId(userId)).thenReturn(expectedTickets);

        // When
        List<Ticket> actualTickets = ticketService.getTicketsByUserId(userId);

        // Then
        assertAll(
                () -> assertNotNull(actualTickets, "Tickets list should not be null"),
                () -> assertEquals(expectedTickets.size(), actualTickets.size(), "Tickets list size should match"),
                () -> assertEquals(expectedTickets, actualTickets, "Tickets list should match the expected list")
        );
        verify(ticketRepository, times(1)).findByUserUserId(userId);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MAX_VALUE})
    @DisplayName("Should return empty list for invalid user IDs")
    void shouldReturnEmptyListForInvalidUserIds(Integer userId) {
        // Given
        when(ticketRepository.findByUserUserId(userId)).thenReturn(List.of());

        // When
        List<Ticket> actualTickets = ticketService.getTicketsByUserId(userId);

        // Then
        assertAll(
                () -> assertNotNull(actualTickets, "Tickets list should not be null"),
                () -> assertTrue(actualTickets.isEmpty(), "Tickets list should be empty")
        );
        verify(ticketRepository, times(1)).findByUserUserId(userId);
    }
}
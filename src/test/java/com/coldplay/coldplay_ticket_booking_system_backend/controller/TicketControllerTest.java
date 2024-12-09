package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.model.Ticket;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.TicketRepository;
import com.coldplay.coldplay_ticket_booking_system_backend.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    private List<Ticket> ticketList;

    @BeforeEach
    void setUp() {
        ticketRepository.deleteAll(); // Clear the repository before each test

        ticketList = Arrays.asList(
                new Ticket(null, null, null, "TICKET" + System.currentTimeMillis(), LocalDateTime.now(), "A1", "issued"),
                new Ticket(null, null, null, "TICKET" + (System.currentTimeMillis() + 1), LocalDateTime.now(), "A2", "issued")
        );

        ticketRepository.save(ticketList.get(0));
        ticketRepository.save(ticketList.get(1));
    }

//    @Test
//    void should_ReturnAllTickets_when_GetTicketsByUserId_given_ValidRequest() throws Exception {
//        // Given
//        Integer userId = 1;
//        when(ticketService.getTicketsByUserId(userId)).thenReturn(ticketList);
//
//        // When & Then
//        mockMvc.perform(get("/tickets/user/{userId}", userId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{\"ticketId\":1,\"orderTicket\":null,\"user\":null,\"ticketNumber\":\"TICKET12345\",\"issueDate\":\"" + LocalDateTime.now() + "\",\"seat\":\"A1\",\"status\":\"issued\"},{\"ticketId\":2,\"orderTicket\":null,\"user\":null,\"ticketNumber\":\"TICKET12346\",\"issueDate\":\"" + LocalDateTime.now() + "\",\"seat\":\"A2\",\"status\":\"issued\"}]"));
//    }

    @ParameterizedTest
    @ValueSource(strings = {"/tickets/user/1"})
    void should_ReturnEmptyList_when_GetTicketsByUserId_given_NoTickets(String url) throws Exception {
        // Given
        when(ticketService.getTicketsByUserId(1)).thenReturn(Collections.emptyList());

        // When & Then
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
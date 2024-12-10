package com.coldplay.coldplay_ticket_booking_system_backend.controller;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.concert.ConcertResponse;
import com.coldplay.coldplay_ticket_booking_system_backend.model.Concert;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.ConcertRepository;
import com.coldplay.coldplay_ticket_booking_system_backend.service.ConcertService;
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
class ConcertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ConcertService concertService;

    @Autowired
    private ConcertRepository concertRepository;

    private List<Concert> concertList;

    @BeforeEach
    void setUp() {
        concertRepository.deleteAll();
        concertRepository.flush();

        concertList = Arrays.asList(
                new Concert(null, "Coldplay Live", LocalDateTime.parse("2024-12-25T20:00:00"), "Madison Square Garden", "New York", "USA", "Coldplay live concert", "http://example.com/image1.jpg", LocalDateTime.parse("2024-11-01T10:00:00"), LocalDateTime.parse("2024-12-24T23:59:00"), 20000),
                new Concert(null, "Adele Live", LocalDateTime.parse("2024-12-31T21:00:00"), "Staples Center", "Los Angeles", "USA", "Adele live concert", "http://example.com/image2.jpg", LocalDateTime.of(2024, 11, 15, 10, 0), LocalDateTime.of(2024, 12, 30, 23, 59), 18000)
        );

        concertRepository.save(concertList.get(0));
        concertRepository.save(concertList.get(1));
    }

    @Test
    void should_ReturnAllConcerts_when_GetAllConcerts_given_ValidRequest() throws Exception {
        // Given
        when(concertService.getAllConcertResponses()).thenReturn(Arrays.asList(
                new ConcertResponse("December 25", "2024", "Madison Square Garden", "New York, USA", "SOLD OUT"),
                new ConcertResponse("December 31", "2024", "Staples Center", "Los Angeles, USA", "SOLD OUT")
        ));

        // When & Then
        mockMvc.perform(get("/api/concerts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"date\":\"December 25\",\"year\":\"2024\",\"venue\":\"Madison Square Garden\",\"location\":\"New York, USA\",\"status\":\"SOLD OUT\"},{\"date\":\"December 31\",\"year\":\"2024\",\"venue\":\"Staples Center\",\"location\":\"Los Angeles, USA\",\"status\":\"SOLD OUT\"}]"));
    }

}
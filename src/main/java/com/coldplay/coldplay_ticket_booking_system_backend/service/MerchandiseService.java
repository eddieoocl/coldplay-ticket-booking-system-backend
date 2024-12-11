package com.coldplay.coldplay_ticket_booking_system_backend.service;

import com.coldplay.coldplay_ticket_booking_system_backend.dto.merchandise.MerchandiseDTO;
import com.coldplay.coldplay_ticket_booking_system_backend.model.Merchandise;
import com.coldplay.coldplay_ticket_booking_system_backend.repository.MerchandiseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;

    public List<MerchandiseDTO> getAllByConcertId(int concertId) {
        return merchandiseRepository.findAllByConcert_ConcertId(concertId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MerchandiseDTO convertToDTO(Merchandise merchandise) {
        return new MerchandiseDTO(
                merchandise.getMerchandiseId(),
                merchandise.getName(),
                merchandise.getPrice(),
                merchandise.getImageUrl(),
                merchandise.getIsCharity()
        );
    }
}
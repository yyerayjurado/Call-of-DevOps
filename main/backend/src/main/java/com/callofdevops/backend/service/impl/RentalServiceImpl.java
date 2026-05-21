package com.callofdevops.backend.service.impl;

import com.callofdevops.backend.dto.RentalDTO;
import com.callofdevops.backend.entity.Rental;
import com.callofdevops.backend.entity.User;
import com.callofdevops.backend.entity.VideoGame;
import com.callofdevops.backend.exception.ResourceNotFoundException;
import com.callofdevops.backend.repository.RentalRepository;
import com.callofdevops.backend.repository.UserRepository;
import com.callofdevops.backend.repository.VideoGameRepository;
import com.callofdevops.backend.service.RentalService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final VideoGameRepository videoGameRepository;

    public RentalServiceImpl(RentalRepository rentalRepository,
                             UserRepository userRepository,
                             VideoGameRepository videoGameRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.videoGameRepository = videoGameRepository;
    }

    @Override
    public RentalDTO createRental(RentalDTO rentalDTO) {
        User customer = userRepository.findById(rentalDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        VideoGame game = videoGameRepository.findById(rentalDTO.getVideoGameId())
                .orElseThrow(() -> new ResourceNotFoundException("Videojuego no encontrado"));

        if (game.getStock() < 1) {
            throw new IllegalArgumentException("Stock insuficiente para el alquiler");
        }

        game.setStock(game.getStock() - 1);
        videoGameRepository.save(game);

        Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setVideoGame(game);
        rental.setStartDate(rentalDTO.getStartDate());
        rental.setExpectedReturnDate(rentalDTO.getExpectedReturnDate());
        rental.setRentalPrice(game.getRentalPrice());
        rental.setTotalPrice(game.getRentalPrice());
        rental.setStatus("ACTIVE");

        Rental saved = rentalRepository.save(rental);
        return mapToDTO(saved);
    }

    @Override
    public List<RentalDTO> findRentalsByCustomer(Long customerId) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return rentalRepository.findByCustomer(customer).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private RentalDTO mapToDTO(Rental rental) {
        RentalDTO dto = new RentalDTO();
        dto.setId(rental.getId());
        dto.setCustomerId(rental.getCustomer().getId());
        dto.setVideoGameId(rental.getVideoGame().getId());
        dto.setStartDate(rental.getStartDate());
        dto.setExpectedReturnDate(rental.getExpectedReturnDate());
        dto.setActualReturnDate(rental.getActualReturnDate());
        dto.setRentalPrice(rental.getRentalPrice());
        dto.setTotalPrice(rental.getTotalPrice());
        dto.setStatus(rental.getStatus());
        return dto;
    }
}

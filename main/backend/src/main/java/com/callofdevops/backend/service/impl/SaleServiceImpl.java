package com.callofdevops.backend.service.impl;

import com.callofdevops.backend.dto.SaleDTO;
import com.callofdevops.backend.entity.Sale;
import com.callofdevops.backend.entity.User;
import com.callofdevops.backend.entity.VideoGame;
import com.callofdevops.backend.exception.ResourceNotFoundException;
import com.callofdevops.backend.repository.SaleRepository;
import com.callofdevops.backend.repository.UserRepository;
import com.callofdevops.backend.repository.VideoGameRepository;
import com.callofdevops.backend.service.SaleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final VideoGameRepository videoGameRepository;

    public SaleServiceImpl(SaleRepository saleRepository, UserRepository userRepository, VideoGameRepository videoGameRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.videoGameRepository = videoGameRepository;
    }

    @Override
    public SaleDTO createSale(SaleDTO saleDTO) {
        User customer = userRepository.findById(saleDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        VideoGame game = videoGameRepository.findById(saleDTO.getVideoGameId())
                .orElseThrow(() -> new ResourceNotFoundException("Videojuego no encontrado"));

        if (game.getStock() < saleDTO.getQuantity()) {
            throw new IllegalArgumentException("Stock insuficiente para la venta");
        }

        game.setStock(game.getStock() - saleDTO.getQuantity());
        videoGameRepository.save(game);

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setVideoGame(game);
        sale.setQuantity(saleDTO.getQuantity());
        sale.setTotalPrice(game.getSalePrice().multiply(BigDecimal.valueOf(saleDTO.getQuantity())));
        sale.setSaleDate(LocalDateTime.now());
        sale.setStatus("COMPLETED");

        Sale saved = saleRepository.save(sale);
        return mapToDTO(saved);
    }

    @Override
    public List<SaleDTO> findSalesByCustomer(Long customerId) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return saleRepository.findByCustomer(customer).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private SaleDTO mapToDTO(Sale sale) {
        SaleDTO dto = new SaleDTO();
        dto.setId(sale.getId());
        dto.setCustomerId(sale.getCustomer().getId());
        dto.setVideoGameId(sale.getVideoGame().getId());
        dto.setQuantity(sale.getQuantity());
        dto.setTotalPrice(sale.getTotalPrice());
        dto.setSaleDate(sale.getSaleDate());
        dto.setStatus(sale.getStatus());
        return dto;
    }
}

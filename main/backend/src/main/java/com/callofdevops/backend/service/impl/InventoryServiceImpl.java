package com.callofdevops.backend.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.callofdevops.backend.dto.InventoryDTO;
import com.callofdevops.backend.entity.Inventory;
import com.callofdevops.backend.exception.ResourceNotFoundException;
import com.callofdevops.backend.repository.InventoryRepository;
import com.callofdevops.backend.repository.VideoGameRepository;
import com.callofdevops.backend.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, VideoGameRepository videoGameRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<InventoryDTO> listInventory() {
        return inventoryRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con id: " + id));

        inventory.setAvailableStock(inventoryDTO.getAvailableStock());
        inventory.setReservedStock(inventoryDTO.getReservedStock());
        inventory.setUpdatedAt(LocalDateTime.now());

        return mapToDTO(inventoryRepository.save(inventory));
    }

    private InventoryDTO mapToDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setId(inventory.getId());
        dto.setVideoGameId(inventory.getVideoGame().getId());
        dto.setAvailableStock(inventory.getAvailableStock());
        dto.setReservedStock(inventory.getReservedStock());
        dto.setUpdatedAt(inventory.getUpdatedAt());
        return dto;
    }
}

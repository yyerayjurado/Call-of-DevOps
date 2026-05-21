package com.callofdevops.backend.service;

import com.callofdevops.backend.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> listInventory();
    InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO);
}

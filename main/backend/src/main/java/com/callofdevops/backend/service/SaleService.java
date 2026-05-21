package com.callofdevops.backend.service;

import com.callofdevops.backend.dto.SaleDTO;

import java.util.List;

public interface SaleService {
    SaleDTO createSale(SaleDTO saleDTO);
    List<SaleDTO> findSalesByCustomer(Long customerId);
}

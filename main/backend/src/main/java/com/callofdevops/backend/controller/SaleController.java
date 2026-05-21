package com.callofdevops.backend.controller;

import com.callofdevops.backend.dto.SaleDTO;
import com.callofdevops.backend.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PreAuthorize("hasAnyRole('CLIENT','WORKER','ADMIN')")
    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody SaleDTO saleDTO) {
        return ResponseEntity.ok(saleService.createSale(saleDTO));
    }

    @PreAuthorize("hasAnyRole('CLIENT','WORKER','ADMIN')")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SaleDTO>> getSalesByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(saleService.findSalesByCustomer(customerId));
    }
}

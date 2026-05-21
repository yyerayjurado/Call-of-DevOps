package com.callofdevops.backend.controller;

import com.callofdevops.backend.dto.RentalDTO;
import com.callofdevops.backend.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PreAuthorize("hasAnyRole('CLIENT','WORKER','ADMIN')")
    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@Valid @RequestBody RentalDTO rentalDTO) {
        return ResponseEntity.ok(rentalService.createRental(rentalDTO));
    }

    @PreAuthorize("hasAnyRole('CLIENT','WORKER','ADMIN')")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<RentalDTO>> getRentalsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(rentalService.findRentalsByCustomer(customerId));
    }
}

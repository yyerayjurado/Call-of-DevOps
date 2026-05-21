package com.callofdevops.backend.service;

import com.callofdevops.backend.dto.RentalDTO;

import java.util.List;

public interface RentalService {
    RentalDTO createRental(RentalDTO rentalDTO);
    List<RentalDTO> findRentalsByCustomer(Long customerId);
}

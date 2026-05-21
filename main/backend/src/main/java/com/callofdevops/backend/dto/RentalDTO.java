package com.callofdevops.backend.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RentalDTO {
    private Long id;
    private Long videoGameId;
    private Long customerId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate expectedReturnDate;

    private LocalDate actualReturnDate;
    private BigDecimal rentalPrice;
    private BigDecimal totalPrice;
    private String status;
}

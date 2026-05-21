package com.callofdevops.backend.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReturnDTO {
    private Long id;
    private Long rentalId;
    private Long customerId;
    private Long videoGameId;

    @NotNull
    private LocalDateTime returnDate;

    private BigDecimal lateFee;
    private String status;
    private String notes;
}

package com.callofdevops.backend.dto;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SaleDTO {
    private Long id;
    private Long videoGameId;
    private Long customerId;

    @NotNull
    @Min(1)
    private Integer quantity;

    private BigDecimal totalPrice;
    private LocalDateTime saleDate;
    private String status;
}

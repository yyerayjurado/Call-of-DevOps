package com.callofdevops.backend.dto;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class InventoryDTO {
    private Long id;
    private Long videoGameId;

    @NotNull
    @Min(0)
    private Integer availableStock;

    @NotNull
    @Min(0)
    private Integer reservedStock;

    private LocalDateTime updatedAt;
}

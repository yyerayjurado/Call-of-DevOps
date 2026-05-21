package com.callofdevops.backend.dto;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VideoGameDTO {
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String category;

    @NotNull
    private BigDecimal salePrice;

    @NotNull
    private BigDecimal rentalPrice;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    private Boolean available;

    private LocalDate releaseDate;
    private String coverImage;
}

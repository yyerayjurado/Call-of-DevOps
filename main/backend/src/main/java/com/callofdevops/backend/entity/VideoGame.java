package com.callofdevops.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "videojuegos")
@NoArgsConstructor
@AllArgsConstructor
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1200)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal salePrice;

    @Column(nullable = false)
    private BigDecimal rentalPrice;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Boolean available;

    private LocalDate releaseDate;

    private String coverImage;
}

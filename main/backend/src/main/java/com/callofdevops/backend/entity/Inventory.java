package com.callofdevops.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inventario")
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private VideoGame videoGame;

    @Column(nullable = false)
    private Integer availableStock;

    @Column(nullable = false)
    private Integer reservedStock;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}

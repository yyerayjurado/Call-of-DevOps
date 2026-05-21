package com.callofdevops.backend.repository;

import com.callofdevops.backend.entity.Inventory;
import com.callofdevops.backend.entity.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByVideoGame(VideoGame videoGame);
}

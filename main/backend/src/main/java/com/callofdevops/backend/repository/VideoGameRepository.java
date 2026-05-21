package com.callofdevops.backend.repository;

import com.callofdevops.backend.entity.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
    List<VideoGame> findByCategoryContainingIgnoreCase(String category);
    List<VideoGame> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT v FROM VideoGame v WHERE v.available = true")
    List<VideoGame> findAvailableGames();
}

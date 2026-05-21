package com.callofdevops.backend.controller;

import com.callofdevops.backend.dto.VideoGameDTO;
import com.callofdevops.backend.service.VideoGameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class VideoGameController {

    private final VideoGameService videoGameService;

    public VideoGameController(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @GetMapping
    public ResponseEntity<List<VideoGameDTO>> getAll() {
        return ResponseEntity.ok(videoGameService.listAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoGameDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(videoGameService.getGameById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<VideoGameDTO>> search(@RequestParam String category) {
        return ResponseEntity.ok(videoGameService.searchByCategory(category));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<VideoGameDTO> create(@Valid @RequestBody VideoGameDTO gameDTO) {
        return ResponseEntity.ok(videoGameService.createGame(gameDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<VideoGameDTO> update(@PathVariable Long id, @Valid @RequestBody VideoGameDTO gameDTO) {
        return ResponseEntity.ok(videoGameService.updateGame(id, gameDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        videoGameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}

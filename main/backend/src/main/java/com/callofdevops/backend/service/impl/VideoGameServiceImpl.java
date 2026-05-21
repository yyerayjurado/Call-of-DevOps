package com.callofdevops.backend.service.impl;

import com.callofdevops.backend.dto.VideoGameDTO;
import com.callofdevops.backend.entity.VideoGame;
import com.callofdevops.backend.exception.ResourceNotFoundException;
import com.callofdevops.backend.repository.VideoGameRepository;
import com.callofdevops.backend.service.VideoGameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoGameServiceImpl implements VideoGameService {

    private final VideoGameRepository videoGameRepository;

    public VideoGameServiceImpl(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    @Override
    public VideoGameDTO createGame(VideoGameDTO gameDTO) {
        return mapToDTO(videoGameRepository.save(mapToEntity(gameDTO)));
    }

    @Override
    public List<VideoGameDTO> listAllGames() {
        return videoGameRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public VideoGameDTO getGameById(Long id) {
        return mapToDTO(videoGameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Videojuego no encontrado con id: " + id)));
    }

    @Override
    public VideoGameDTO updateGame(Long id, VideoGameDTO gameDTO) {
        VideoGame game = videoGameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Videojuego no encontrado con id: " + id));
        game.setTitle(gameDTO.getTitle());
        game.setDescription(gameDTO.getDescription());
        game.setCategory(gameDTO.getCategory());
        game.setSalePrice(gameDTO.getSalePrice());
        game.setRentalPrice(gameDTO.getRentalPrice());
        game.setStock(gameDTO.getStock());
        game.setAvailable(gameDTO.getAvailable());
        game.setReleaseDate(gameDTO.getReleaseDate());
        game.setCoverImage(gameDTO.getCoverImage());
        return mapToDTO(videoGameRepository.save(game));
    }

    @Override
    public void deleteGame(Long id) {
        videoGameRepository.deleteById(id);
    }

    @Override
    public List<VideoGameDTO> searchByCategory(String category) {
        return videoGameRepository.findByCategoryContainingIgnoreCase(category).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private VideoGameDTO mapToDTO(VideoGame game) {
        VideoGameDTO dto = new VideoGameDTO();
        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setDescription(game.getDescription());
        dto.setCategory(game.getCategory());
        dto.setSalePrice(game.getSalePrice());
        dto.setRentalPrice(game.getRentalPrice());
        dto.setStock(game.getStock());
        dto.setAvailable(game.getAvailable());
        dto.setReleaseDate(game.getReleaseDate());
        dto.setCoverImage(game.getCoverImage());
        return dto;
    }

    private VideoGame mapToEntity(VideoGameDTO dto) {
        VideoGame game = new VideoGame();
        game.setTitle(dto.getTitle());
        game.setDescription(dto.getDescription());
        game.setCategory(dto.getCategory());
        game.setSalePrice(dto.getSalePrice());
        game.setRentalPrice(dto.getRentalPrice());
        game.setStock(dto.getStock());
        game.setAvailable(dto.getAvailable());
        game.setReleaseDate(dto.getReleaseDate());
        game.setCoverImage(dto.getCoverImage());
        return game;
    }
}

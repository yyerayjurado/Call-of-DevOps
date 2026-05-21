package com.callofdevops.backend.service;

import com.callofdevops.backend.dto.VideoGameDTO;

import java.util.List;

public interface VideoGameService {
    VideoGameDTO createGame(VideoGameDTO gameDTO);
    List<VideoGameDTO> listAllGames();
    VideoGameDTO getGameById(Long id);
    VideoGameDTO updateGame(Long id, VideoGameDTO gameDTO);
    void deleteGame(Long id);
    List<VideoGameDTO> searchByCategory(String category);
}

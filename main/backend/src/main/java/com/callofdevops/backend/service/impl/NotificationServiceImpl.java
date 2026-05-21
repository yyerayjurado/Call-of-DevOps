package com.callofdevops.backend.service.impl;

import com.callofdevops.backend.dto.NotificationDTO;
import com.callofdevops.backend.entity.Notification;
import com.callofdevops.backend.entity.User;
import com.callofdevops.backend.exception.ResourceNotFoundException;
import com.callofdevops.backend.repository.NotificationRepository;
import com.callofdevops.backend.repository.UserRepository;
import com.callofdevops.backend.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<NotificationDTO> getUserNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return notificationRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private NotificationDTO mapToDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setUserId(notification.getUser().getId());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setReadFlag(notification.getReadFlag());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;
    }
}

package com.callofdevops.backend.service;

import com.callofdevops.backend.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getUserNotifications(Long userId);
}

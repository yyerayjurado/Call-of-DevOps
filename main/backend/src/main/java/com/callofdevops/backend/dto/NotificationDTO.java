package com.callofdevops.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private Long id;
    private Long userId;
    private String message;
    private String type;
    private Boolean readFlag;
    private LocalDateTime createdAt;
}

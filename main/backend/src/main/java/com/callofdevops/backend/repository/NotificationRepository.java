package com.callofdevops.backend.repository;

import com.callofdevops.backend.entity.Notification;
import com.callofdevops.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
}

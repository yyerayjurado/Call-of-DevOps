package com.callofdevops.backend.service;

import com.callofdevops.backend.dto.AuthRequest;
import com.callofdevops.backend.dto.AuthResponse;
import com.callofdevops.backend.dto.UserDTO;

public interface AuthService {
    AuthResponse authenticate(AuthRequest request);
    AuthResponse register(UserDTO userDTO);
}

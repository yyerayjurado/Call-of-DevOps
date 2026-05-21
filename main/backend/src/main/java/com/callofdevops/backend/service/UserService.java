package com.callofdevops.backend.service;

import com.callofdevops.backend.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long id);
    List<UserDTO> listAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deactivateUser(Long id);
}

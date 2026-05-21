package com.callofdevops.backend.service.impl;

import com.callofdevops.backend.dto.UserDTO;
import com.callofdevops.backend.entity.Role;
import com.callofdevops.backend.entity.User;
import com.callofdevops.backend.exception.ResourceNotFoundException;
import com.callofdevops.backend.repository.RoleRepository;
import com.callofdevops.backend.repository.UserRepository;
import com.callofdevops.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return mapToDTO(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id)));
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        Role role = roleRepository.findByName(userDTO.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        user.setRole(role);
        user.setActive(userDTO.getActive());
        return mapToDTO(userRepository.save(user));
    }

    @Override
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        user.setActive(false);
        userRepository.save(user);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().getName());
        dto.setActive(user.getActive());
        return dto;
    }
}

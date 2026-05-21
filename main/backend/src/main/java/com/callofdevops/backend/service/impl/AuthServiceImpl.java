package com.callofdevops.backend.service.impl;

import com.callofdevops.backend.dto.AuthRequest;
import com.callofdevops.backend.dto.AuthResponse;
import com.callofdevops.backend.dto.UserDTO;
import com.callofdevops.backend.entity.Role;
import com.callofdevops.backend.entity.User;
import com.callofdevops.backend.exception.ResourceNotFoundException;
import com.callofdevops.backend.repository.RoleRepository;
import com.callofdevops.backend.repository.UserRepository;
import com.callofdevops.backend.service.AuthService;
import com.callofdevops.backend.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new ResourceNotFoundException("Credenciales inválidas");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), java.util.Collections.emptyList()));

        return new AuthResponse(token, user.getEmail(), user.getRole().getName());
    }

    @Override
    public AuthResponse register(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        Role role = roleRepository.findByName(userDTO.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

        User newUser = new User();
        newUser.setFullName(userDTO.getFullName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRole(role);
        newUser.setActive(Boolean.TRUE);

        userRepository.save(newUser);
        String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                newUser.getEmail(), newUser.getPassword(), java.util.Collections.emptyList()));

        return new AuthResponse(token, newUser.getEmail(), newUser.getRole().getName());
    }
}

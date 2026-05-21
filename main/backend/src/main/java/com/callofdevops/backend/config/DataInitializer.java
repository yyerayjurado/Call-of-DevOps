package com.callofdevops.backend.config;

import com.callofdevops.backend.entity.Role;
import com.callofdevops.backend.entity.User;
import com.callofdevops.backend.entity.VideoGame;
import com.callofdevops.backend.repository.RoleRepository;
import com.callofdevops.backend.repository.UserRepository;
import com.callofdevops.backend.repository.VideoGameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(RoleRepository roleRepository,
                                             UserRepository userRepository,
                                             VideoGameRepository gameRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.saveAll(List.of(
                        new Role(null, "ROLE_ADMIN"),
                        new Role(null, "ROLE_CLIENT"),
                        new Role(null, "ROLE_WORKER")
                ));
            }

            if (userRepository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow();
                Role clientRole = roleRepository.findByName("ROLE_CLIENT").orElseThrow();
                Role workerRole = roleRepository.findByName("ROLE_WORKER").orElseThrow();

                userRepository.saveAll(Arrays.asList(
                        new User(null, "Admin DevOps", "admin@devops.com", encoder.encode("Admin123!"), adminRole, true),
                        new User(null, "Cliente Demo", "cliente@devops.com", encoder.encode("Cliente123!"), clientRole, true),
                        new User(null, "Worker DevOps", "worker@devops.com", encoder.encode("Worker123!"), workerRole, true)
                ));
            }

            if (gameRepository.count() == 0) {
                gameRepository.saveAll(List.of(
                        new VideoGame(null, "Cyber Heist", "Acción futurista con cobro y alquiler.", "Acción", new BigDecimal("59.90"), new BigDecimal("9.90"), 24, true, LocalDate.of(2025, 11, 15), "/assets/covers/cyber-heist.jpg"),
                        new VideoGame(null, "Legado de Arena", "Aventura épica de mundo abierto.", "Aventura", new BigDecimal("49.90"), new BigDecimal("7.50"), 18, true, LocalDate.of(2024, 8, 2), "/assets/covers/legado-arena.jpg"),
                        new VideoGame(null, "Simulador de DevOps", "Simulación profesional de operaciones y despliegues.", "Simulación", new BigDecimal("34.90"), new BigDecimal("5.90"), 12, true, LocalDate.of(2025, 3, 8), "/assets/covers/simulador-devops.jpg")
                ));
            }
        };
    }
}

package com.callofdevops.backend.repository;

import com.callofdevops.backend.entity.Rental;
import com.callofdevops.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByCustomer(User customer);
}

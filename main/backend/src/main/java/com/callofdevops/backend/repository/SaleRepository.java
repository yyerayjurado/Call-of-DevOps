package com.callofdevops.backend.repository;

import com.callofdevops.backend.entity.Sale;
import com.callofdevops.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByCustomer(User customer);
}

package com.example.DynoOptical.repository;

import com.example.DynoOptical.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
     Optional<Cart> findByCustomerId(Long customerId);
}

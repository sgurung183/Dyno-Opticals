package com.example.DynoOptical.repository;

import com.example.DynoOptical.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

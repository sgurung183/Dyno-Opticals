package com.example.DynoOptical.repository;

import com.example.DynoOptical.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

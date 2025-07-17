package com.example.DynoOptical.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime orderDate;

    // OrderStatus field with @Enumerated(EnumType.STRING) —
    // otherwise JPA will store it as an integer (ordinal) by default
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private double orderTotal;

}

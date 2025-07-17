package com.example.DynoOptical.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    //This tells JPA:
    //“This order can have many line items, and they are stored in the LineItem table under the order column.”
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> orderItems;

}

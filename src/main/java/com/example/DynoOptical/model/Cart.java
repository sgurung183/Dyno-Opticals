package com.example.DynoOptical.model;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    //one customer has one cart
    //cart belongs to this customer
    @JoinColumn(name = "Customer_id")
    private Customer customer;

    //This is telling the JPA LineItem owns the foreign key (not Cart). Don’t create an extra table
    //mappedBy = "cart" means the LineItem class has the @ManyToOne field cart
    //It tells Spring Boot to manage the relationship from the LineItem side
    //So when you save the cart, JPA looks inside the lineItems and knows how to map them

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> cartItems;

    private double cartTotal;


}

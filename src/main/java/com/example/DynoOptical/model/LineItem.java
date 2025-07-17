package com.example.DynoOptical.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id") //foreign key to item table
    private Item item;

    private int quantity;
    private double lineTotal;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    //there can be many lineItems in one cart
    //this is saying line item belongs to that cart
    private Cart cart;

}

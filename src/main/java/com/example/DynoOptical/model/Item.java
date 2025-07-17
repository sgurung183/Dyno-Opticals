package com.example.DynoOptical.model;
import jakarta.persistence.*;
import com.example.DynoOptical.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double price;

    //makes sure category is saved as text in the DB not int like 0, 1
    @Enumerated(EnumType.STRING)
    private Category category;

    private int stock;
    private String imageUrl;
    private String description;
}
//item does not need a refencet ot lineItem
//items ddo not need to know here thye;re used... okkkk
package com.example.DynoOptical.repository;

import com.example.DynoOptical.model.Category;
import com.example.DynoOptical.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    //Spring Data JPA will automatically generate the query based on this method name!
    List<Item> findByCategory(Category category);

    List<Item> findByName(String name);

    List<Item> findByNameContainingIgnoreCase(String name);


}

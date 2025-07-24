package com.example.DynoOptical.repository;

import com.example.DynoOptical.model.Cart;
import com.example.DynoOptical.model.Item;
import com.example.DynoOptical.model.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {

    //Find the LineItem where:
        //it belongs to a specific Cart
        //and references a specific Item
    public Optional<LineItem> findByCartAndItem(Cart cart, Item item);

    public List<LineItem> findByCart(Cart cart);
}

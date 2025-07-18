package com.example.DynoOptical.service;

import com.example.DynoOptical.model.Category;
import com.example.DynoOptical.model.Item;
import com.example.DynoOptical.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public List<Item> getItemsByCategory(Category category){
        return itemRepository.findByCategory(category);
    }

    public List<Item> getItemsByName(String name){
        return itemRepository.findByName(name);
    }
}

package com.example.DynoOptical.controller;

import com.example.DynoOptical.model.Category;
import com.example.DynoOptical.model.Item;
import com.example.DynoOptical.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;


    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //when you are posting meaning you're creating something or adding new info to the backend
    @PostMapping("/create")
    //@RequestBody Item item “Take the JSON body from the HTTP request, and automatically convert it into an Item object.”
    //RespinseEntity is the full HTTP response
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item saved = itemService.createItem(item);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Item>> findItem(@RequestParam String name){
        List<Item> searched = itemService.getItemsByName(name);
        return new ResponseEntity<>(searched,HttpStatus.OK);

    }

    @GetMapping("/category")
    public ResponseEntity<List<Item>> findItemByCategory(@RequestParam Category category){
        List<Item> items = itemService.getItemsByCategory(category);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<Item>> viewAllItems(){
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}

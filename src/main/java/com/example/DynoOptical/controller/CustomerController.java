package com.example.DynoOptical.controller;

import com.example.DynoOptical.model.Customer;
import com.example.DynoOptical.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/customer")

public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/guest")
    public ResponseEntity<Customer> createGuestCustomer(){
        Customer createdGuest = customerService.createGuestCustomer();
        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    //since updating existing record PutMapping
    @PutMapping("/register/{customerId}")
    public ResponseEntity<Customer> registerGuest(@PathVariable Long customerId, @RequestBody Customer updatedInfo){
        Customer updatedGuest = customerService.registerGuest(customerId, updatedInfo);
        return new ResponseEntity<>(updatedGuest, HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<Customer>> viewAll(){
        List<Customer> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
}

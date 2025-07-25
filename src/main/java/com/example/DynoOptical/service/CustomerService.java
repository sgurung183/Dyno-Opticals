package com.example.DynoOptical.service;

import com.example.DynoOptical.model.Cart;
import com.example.DynoOptical.model.Customer;
import com.example.DynoOptical.repository.CartRepository;
import com.example.DynoOptical.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer does not exist"));

    }

    //guest account for customer
    //guest account created and a cart assigned to them as well
    public Customer createGuestCustomer() {
        Customer guestCustomer = new Customer();
        Cart customerCart = new Cart();
        customerCart.setCustomer(guestCustomer);
        customerRepository.save(guestCustomer);
        cartRepository.save(customerCart);
        return guestCustomer;
    }

    public Customer registerGuest(Long customerId, Customer updatedInfo) {
        Customer guestCustomer = getCustomerById(customerId);

        guestCustomer.setName(updatedInfo.getName());
        guestCustomer.setEmail(updatedInfo.getEmail());
        guestCustomer.setPhone(updatedInfo.getPhone());
        guestCustomer.setAddress(updatedInfo.getAddress());

        customerRepository.save(guestCustomer);

        return guestCustomer;

    }
}

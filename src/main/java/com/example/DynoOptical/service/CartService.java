package com.example.DynoOptical.service;

import com.example.DynoOptical.model.Cart;
import com.example.DynoOptical.model.Customer;
import com.example.DynoOptical.model.Item;
import com.example.DynoOptical.model.LineItem;
import com.example.DynoOptical.repository.CartRepository;
import com.example.DynoOptical.repository.CustomerRepository;
import com.example.DynoOptical.repository.ItemRepository;
import com.example.DynoOptical.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    //To save/fetch the customer's cart
    private CartRepository cartRepository;

    @Autowired
    //To get the customer object (using customerId)
    private CustomerRepository customerRepository;

    @Autowired
    //To fetch the product being added to the cart
    private ItemRepository itemRepository;

    @Autowired
    //To save or update individual line items
    private LineItemRepository lineItemRepository;

    public Cart getCartByCustomerId(Long customerId){
      Optional<Cart> optionalCart = cartRepository.findByCustomerId(customerId);

      return optionalCart.orElseGet(() -> {
          Customer customer = customerRepository.findById(customerId)
                  .orElseThrow(()->new RuntimeException("Customer not found with this ID: " + customerId));
          Cart newCart = new Cart();
          newCart.setCustomer(customer);
          return cartRepository.save(newCart);
      });

      //if cart exists, return it else create it for the customer
    }

    public Cart addItemToCart(Long customerId, Long itemId){
        //get the cart of the customer
        Cart customerCart = getCartByCustomerId(customerId);

        Item item = itemRepository.findById(itemId)
                .orElseThrow(()->new RuntimeException("ITEM DOES NOT EXIST, Item ID: " + itemId));
        //check if the line item exists
        Optional<LineItem> optionalLineItem = lineItemRepository.findByCartAndItem(customerCart, item);

        if(optionalLineItem.isPresent()){
            //line item exists so update the quantity
            LineItem lineItem = optionalLineItem.get();
            lineItem.setQuantity(lineItem.getQuantity() + 1);
            lineItemRepository.save(lineItem);
        }
        //lineitem does not exist for that item
        else{
            LineItem lineItem = new LineItem();
            lineItem.setCart(customerCart);
            lineItem.setItem(item);
            lineItem.setQuantity(1);
            lineItemRepository.save(lineItem);
        }
        return customerCart;

    }

    public Cart removeFromCart(Long customerId, Long itemId){
        Cart customerCart = getCartByCustomerId(customerId);

        //if item does not exist throw an error
        Item item = itemRepository.findById(itemId)
                .orElseThrow(()->new RuntimeException("Item does not exist"));

        Optional<LineItem> optionalLineItem = lineItemRepository.findByCartAndItem(customerCart, item);

        if(optionalLineItem.isPresent()){
            LineItem lineItem = optionalLineItem.get();
            int itemQuantity = lineItem.getQuantity();

            if(itemQuantity == 1){
                //remove the whole line item
                lineItemRepository.delete(lineItem);
            }
            else{
                lineItem.setQuantity(itemQuantity - 1);
                lineItemRepository.save(lineItem);
            }

        }
        else{
            throw new RuntimeException("Item not in the cart");
        }

        return customerCart;
    }

    public double calculateCartTotal(Long customerId){
        double total = 0.0;

        Cart customerCart = getCartByCustomerId(customerId);

        //items in the customers cart
        List<LineItem> lineItems = lineItemRepository.findByCart(customerCart);

        for(LineItem lineItem : lineItems){
            total += lineItem.getLineTotal();
        }
        return total;
    }

    public Cart removeLine(Long customerId, Long itemId){
        Cart customerCart = getCartByCustomerId(customerId);

        Item item = itemRepository.findById(itemId)
                .orElseThrow(()->new RuntimeException("Item not sold at this store"));

        Optional<LineItem> optionalLineItem = lineItemRepository.findByCartAndItem(customerCart, item);
        if(optionalLineItem.isPresent()){
            lineItemRepository.delete(optionalLineItem.get());
        }
        else{
            throw new RuntimeException("Item not in the cart");
        }
        return customerCart;
    }

}

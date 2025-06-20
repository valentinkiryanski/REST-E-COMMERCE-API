package com.ecommercenext.nextecommerce.controllers;

import com.ecommercenext.nextecommerce.entity.Cart;
import com.ecommercenext.nextecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("view")
    public List<Cart> viewCart(){
        return cartService.viewCart();
    }

    @PostMapping("/add")
    public List<Cart> addToCart(@RequestBody List<Map<String,Object>> product){
        return cartService.addToCart(product);
    }

    @DeleteMapping("delete/{id}")
    public Cart deleteFromCart(@PathVariable int id){
        return cartService.deleteFromCart(id);
    }

}

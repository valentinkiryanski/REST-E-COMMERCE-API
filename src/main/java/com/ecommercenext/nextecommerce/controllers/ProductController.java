package com.ecommercenext.nextecommerce.controllers;

import com.ecommercenext.nextecommerce.entity.Products;
import com.ecommercenext.nextecommerce.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private ProductsService productsService;


    @Autowired
    public ProductController(ProductsService productsService){
        this.productsService = productsService;
    }

    @PostMapping("/add")
    public Products addProduct(@RequestBody Products product) {
        return productsService.addProduct(product);
    }

    @GetMapping("/view")
    public List<Products> findAll(){
        return productsService.findAll();
    }

    @PatchMapping("/modify/{name}")
    public Products modifyProduct(@PathVariable String name, @RequestBody Map<String,Object> updates){

        return productsService.modifyProduct(name,updates);
    }

    @PatchMapping("modify/id/{id}")
    public Products modifyProduct(@PathVariable int id, @RequestBody Map<String,Object> updates){
        return productsService.modifyProduct(id,updates);
    }




}

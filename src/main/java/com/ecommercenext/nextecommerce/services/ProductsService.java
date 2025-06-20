package com.ecommercenext.nextecommerce.services;

import com.ecommercenext.nextecommerce.custom.CustomProductsResponse;
import com.ecommercenext.nextecommerce.entity.Products;
import com.ecommercenext.nextecommerce.exceptionhandlers.ProductAlreadyExistsException;
import com.ecommercenext.nextecommerce.exceptionhandlers.ProductNotFoundException;
import com.ecommercenext.nextecommerce.repos.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductsService {


    private ProductsRepo productsRepo;

    @Autowired
    public ProductsService(ProductsRepo productsRepo){
        this.productsRepo = productsRepo;
    }

    public ProductsService(){

    }

    public Products findByName(String name){
        Optional<Products> product = productsRepo.findByName(name);
        if(product.isEmpty()){
            throw new ProductNotFoundException("A product with name " + name + " does not exist");
        }
        Products nextProduct = product.get();
        return nextProduct;
    }

    public Products findById(int id){
        Optional<Products> product = productsRepo.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("A product with id " + id + " does not exist");
        }
        Products nextProduct = product.get();
        return nextProduct;
    }

    public List<Products> findAll() {
        return productsRepo.findAll();
    }

    @Transactional
    public Products addProduct(Products product){
        Optional<Products> existingProduct = productsRepo.findByName(product.getName());
        if(existingProduct.isPresent()){
            throw new ProductAlreadyExistsException("Product with name " + product.getName() + " already exists");
        }

        Products saved = productsRepo.save(product);
        return saved;
    }

    @Transactional
    public Products modifyProduct(String name, Map<String,Object> updates){
        Optional<Products> existingProduct = productsRepo.findByName(name);
        if(existingProduct.isEmpty()){
            throw new ProductNotFoundException("Product with name " + name + " cannot be found");
        }if (!updates.containsKey("name") && !updates.containsKey("availableQuantity") && !updates.containsKey("price")) {
            throw new IllegalArgumentException("No valid update keys provided.");
        }


        if(updates.containsKey("name")){
            String nextName = updates.get("name").toString();
            existingProduct.get().setName(nextName);
        }if(updates.containsKey("availableQuantity")){
            int qty = (int)updates.get("availableQuantity");
            existingProduct.get().setAvailableQuantity(qty);
        }if(updates.containsKey("price")) {
            Object value = updates.get("price");
            Double price = Double.valueOf(value.toString());
            existingProduct.get().setPrice(price);
        }

        return productsRepo.save(existingProduct.get());
    }

    @Transactional
    public Products modifyProduct(int id, Map<String,Object> updates){
        Optional<Products> existingProduct = productsRepo.findById(id);
        if(existingProduct.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " cannot be found");
        }
        if (!updates.containsKey("name") && !updates.containsKey("availableQuantity") && !updates.containsKey("price")) {
            throw new IllegalArgumentException("No valid update keys provided.");
        }


        if(updates.containsKey("name")){
            String nextName = (String)updates.get("name");
            existingProduct.get().setName(nextName);
        }if(updates.containsKey("availableQuantity")){
            int qty = (int)updates.get("availableQuantity");
            existingProduct.get().setAvailableQuantity(qty);
        }if(updates.containsKey("price")) {
            Object value = updates.get("price");
            Double price = Double.valueOf(value.toString());
            existingProduct.get().setPrice(price);
        }

        return productsRepo.save(existingProduct.get());
    }

    @Transactional
    public Products modifyById(int id,int qtyChange){
        Optional<Products> product = productsRepo.findById(id);

        if(product.isPresent()){
            Products p = product.get();
            p.setAvailableQuantity(qtyChange);
            productsRepo.save(p);
        }else{
            throw new ProductNotFoundException("Product with id " + id + " could not be found");
        }
        return product.get();
    }


}

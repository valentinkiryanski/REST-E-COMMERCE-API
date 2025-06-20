package com.ecommercenext.nextecommerce.services;

import com.ecommercenext.nextecommerce.entity.Cart;
import com.ecommercenext.nextecommerce.entity.Products;
import com.ecommercenext.nextecommerce.exceptionhandlers.EmptyCartException;
import com.ecommercenext.nextecommerce.exceptionhandlers.NotEnoughQuantityAvailableException;
import com.ecommercenext.nextecommerce.repos.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private CartRepo cartRepo;

    private ProductsService productsService;

    @Autowired
    public CartService(CartRepo cartRepo, ProductsService productsService){
        this.cartRepo = cartRepo;
        this.productsService = productsService;
    }

    public List<Cart> viewCart(){
        return cartRepo.findAll();
    }

    @Transactional
    public void deleteCart(){
        cartRepo.deleteAll();
    }

    @Transactional
    public Cart deleteFromCart(int id){
        Optional<Cart> cart = cartRepo.findById(id);
        if(cart.isEmpty()){
            throw new EmptyCartException("Your cart is already empty");
        }
        Cart nextCart = cart.get();

        cartRepo.delete(nextCart);
        return nextCart;
    }


    @Transactional
    public List<Cart> addToCart(List<Map<String,Object>> product){

        List<Cart> cartList = new ArrayList<>();

        for(Map<String,Object> map : product){
            if(map.containsKey("name") && map.containsKey("quantity")){
                Object value = map.get("name");
                String name = value.toString();
                Object nextValue = map.get("quantity");
                String quantity = nextValue.toString();
                Integer qty = Integer.valueOf(quantity);
                if(qty <= 0){
                    throw new IllegalArgumentException("Quantity must be greater than zero");
                }
                Cart cart = new Cart();
                Products productToAdd = productsService.findByName(name);
                if(qty > productToAdd.getAvailableQuantity()){
                    throw new NotEnoughQuantityAvailableException("You are trying to add more quantity than currently available at the store");
                }
                if(cartRepo.getTotalQuantityInCart(productToAdd.getId()) + qty > productToAdd.getAvailableQuantity()){
                    throw new NotEnoughQuantityAvailableException("You are trying to add more quantity than currently available at the store");
                }

                cart.setQuantity(qty);
                cart.setProductId(productToAdd.getId());
                Double totalPrice = productToAdd.getPrice() * qty;
                cart.setTotalPrice(totalPrice);
                cartRepo.save(cart);
                cartList.add(cart);
            }else{
                throw new IllegalArgumentException("No name or quantity provided");
            }

        }
        return cartList;

    }


}

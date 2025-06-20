package com.ecommercenext.nextecommerce.services;
import com.ecommercenext.nextecommerce.entity.Products;
import com.ecommercenext.nextecommerce.exceptionhandlers.NoProductsInCartException;
import com.ecommercenext.nextecommerce.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommercenext.nextecommerce.entity.Order;
import com.ecommercenext.nextecommerce.entity.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepo orderRepo;

    private CartService cartService;

    private ProductsService productsService;

    @Autowired
    public OrderService(OrderRepo orderRepo, CartService cartService, ProductsService productsService){
        this.orderRepo = orderRepo;
        this.cartService = cartService;
        this.productsService = productsService;
    }

    public List<Order>  viewOrder(){
        return orderRepo.findAll();
    }

    @Transactional
    public List<Order> makeOrder(){
        if(cartService.viewCart().isEmpty()){
            throw new NoProductsInCartException("Your cart is empty. Try adding some product/s first");
        }
        List<Cart> cartItems = cartService.viewCart();

        List<Order> orderItems = new ArrayList<>();

        for(Cart c: cartItems){
            Order order = new Order();
            order.setQuantity(c.getQuantity());
            order.setProductId(c.getProductId());
            order.setTotalPrice(c.getTotalPrice());
            orderItems.add(order);
        }

        cartService.deleteCart();

        for(Order order : orderItems){
            Products product = productsService.findById(order.getProductId());
            int qty = product.getAvailableQuantity() - order.getQuantity();
            productsService.modifyById(product.getId(), qty);
            orderRepo.save(order);
        }

        return orderItems;
    }

}

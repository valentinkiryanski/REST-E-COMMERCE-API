package com.ecommercenext.nextecommerce.controllers;

import com.ecommercenext.nextecommerce.entity.Order;
import com.ecommercenext.nextecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/view")
    public List<Order> viewOrder(){
        return orderService.viewOrder();
    }

    @GetMapping("/make")
    public List<Order> makeOrder(){
        return orderService.makeOrder();
    }


}

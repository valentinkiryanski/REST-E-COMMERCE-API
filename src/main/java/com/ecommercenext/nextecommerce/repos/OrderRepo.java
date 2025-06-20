package com.ecommercenext.nextecommerce.repos;

import com.ecommercenext.nextecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer> {
}

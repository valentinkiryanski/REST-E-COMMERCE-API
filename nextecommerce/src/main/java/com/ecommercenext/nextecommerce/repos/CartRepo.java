package com.ecommercenext.nextecommerce.repos;

import com.ecommercenext.nextecommerce.entity.Cart;
import com.ecommercenext.nextecommerce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Integer> {

    Optional<Cart> findById(int Id);
    @Query("SELECT COALESCE(SUM(c.quantity),0) FROM Cart c WHERE c.productId = :productId")
    int getTotalQuantityInCart(@Param("productId") int productId);
}

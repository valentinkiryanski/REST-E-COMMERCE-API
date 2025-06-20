package com.ecommercenext.nextecommerce.repos;

import com.ecommercenext.nextecommerce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepo extends JpaRepository<Products,Integer> {


    Optional<Products> findByName(String name);
}

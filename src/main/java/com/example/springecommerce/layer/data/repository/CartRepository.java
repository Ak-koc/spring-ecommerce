package com.example.springecommerce.layer.data.repository;

import com.example.springecommerce.layer.data.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    @Query("SELECT MAX(c.cartId) FROM Cart c")
    long getLastId();
}

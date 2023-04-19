package com.example.springecommerce.layer.data.repository;

import com.example.springecommerce.layer.data.entity.CartProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartProductRepository extends CrudRepository<CartProduct, Long> {
    @Query("SELECT cp FROM CartProduct cp WHERE cp.cart.cartId = :cartId AND cp.product.productId = :productId")
    Optional<CartProduct> findCartProduct(@Param("cartId") Long cartId, @Param("productId") Long productId);
}

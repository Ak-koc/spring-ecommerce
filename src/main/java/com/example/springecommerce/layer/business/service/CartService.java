package com.example.springecommerce.layer.business.service;

import com.example.springecommerce.layer.business.dto.CartDto;

public interface CartService {
    CartDto find(long cartId);

    void add(long cartId, long productId);

    void delete(long cartId, long productId);

    void checkout(CartDto cartDto);
}
//    SQL Database
//    Cart cartId customerName cardNumber cartStatus (Enum: NEW, COMPLETED)
//    CartProduct cartProductId cartId productId salesQuantity
//
//        Spring Boot REST Endpoints
//        /cart/get/{id} (Yoksa yarat)
//        /cart/add/{cartid}/{productid}
//        /cart/remove/{cartid}/{productid}
//        /cart/checkout
package com.example.springecommerce.layer.business.dto;

import com.example.springecommerce.layer.data.entity.CartProduct;
import com.example.springecommerce.layer.data.entity.CartStatus;
import jakarta.persistence.*;

import java.util.List;

public class CartDto {

    private long cartId;
    private String customerName;

    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;

    public List<CartProductDto> getCartProductDtoList() {
        return cartProductDtoList;
    }

    public void setCartProductDtoList(List<CartProductDto> cartProductDtoList) {
        this.cartProductDtoList = cartProductDtoList;
    }

    private List<CartProductDto> cartProductDtoList;


    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
    }
}

package com.example.springecommerce.layer.business.dto;

import com.example.springecommerce.layer.data.entity.Cart;
import com.example.springecommerce.layer.data.entity.Product;
import jakarta.persistence.*;

public class CartProductDto  {

    private long cartProductId;

    private int salesQuantity;

    private long cartId;

    private long productId;

    public long getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(long cartProductId) {
        this.cartProductId = cartProductId;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}

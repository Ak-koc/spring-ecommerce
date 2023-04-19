package com.example.springecommerce.layer.presentation.rest;

import com.example.springecommerce.layer.business.dto.CartDto;
import com.example.springecommerce.layer.business.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get/{id}")
    public CartDto getCart(@PathVariable("id") long cartId){
        return cartService.find(cartId);
    }

    @PostMapping("/add/{cartId}/{productId}")
    public String addCartProduct(@PathVariable("cartId") long cartId, @PathVariable("productId") long productId){
        cartService.add(cartId, productId);
        return "Eklendi";
    }

    @DeleteMapping("/remove/{cartId}/{productId}")
    public String removeCartProduct(@PathVariable("cartId") long cartId, @PathVariable("productId") long productId){
        cartService.delete(cartId, productId);
        return "Kaldırıldı";
    }

    @PutMapping("/checkout")
    public String cartCheckout(@RequestBody CartDto cartDto){
        cartService.checkout(cartDto);
        return "Alışveriş tamamlandı";
    }
}

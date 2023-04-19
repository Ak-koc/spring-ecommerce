package com.example.springecommerce.layer.business.service;

import com.example.springecommerce.layer.business.dto.CartDto;
import com.example.springecommerce.layer.business.dto.CartProductDto;
import com.example.springecommerce.layer.business.dto.ProductDto;
import com.example.springecommerce.layer.data.entity.Cart;
import com.example.springecommerce.layer.data.entity.CartProduct;
import com.example.springecommerce.layer.data.entity.CartStatus;
import com.example.springecommerce.layer.data.entity.Product;
import com.example.springecommerce.layer.data.repository.CartProductRepository;
import com.example.springecommerce.layer.data.repository.CartRepository;
import com.example.springecommerce.layer.data.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;
    private CartProductRepository cartProductRepository;
    private ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, CartProductRepository cartProductRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.productRepository = productRepository;
    }


    @Override
    public CartDto find(long cartId) {
        Optional<Cart> optional = cartRepository.findById(cartId);
        if(optional.isPresent()) {
            return toCartDto(optional.get());
        }else{
            Cart cart = new Cart();
            long lastId = cartRepository.getLastId() + 1;
            cart.setCartId(lastId);
            cartRepository.save(cart);
            return toCartDto(cart);
        }
    }

    @Override
    public void add(long cartId, long productId) {
        System.out.println(cartId + " " + productId);
        Optional<CartProduct> cartProduct = cartProductRepository.findCartProduct(cartId, productId);
        if(cartProduct.isPresent()){
            cartProduct.get().setSalesQuantity(cartProduct.get().getSalesQuantity() + 1);
            cartProductRepository.save(cartProduct.get());
        }else{
            CartProductDto cartProductDto = new CartProductDto();
            cartProductDto.setCartProductId(0);
            cartProductDto.setProductId(productId);
            cartProductDto.setCartId(cartId);
            cartProductDto.setSalesQuantity(1);
            cartProductRepository.save(toCartProductEntity(cartProductDto));
        }
    }

    @Override
    public void delete(long cartId, long productId) {
        Optional<CartProduct> cartProduct = cartProductRepository.findCartProduct(cartId, productId);
        if(cartProduct.isPresent()){
            if(cartProduct.get().getSalesQuantity() == 1){
                long x = 14;
                cartProductRepository.delete(cartProduct.get());
            }else{
                cartProduct.get().setSalesQuantity(cartProduct.get().getSalesQuantity() - 1);
                cartProductRepository.save(cartProduct.get());
            }
        }
    }

    @Override
    public void checkout(CartDto cartDto) {
        Cart cart = toCartEntity(cartDto);
        cartRepository.save(cart);
    }

    private Cart toCartEntity(CartDto cartDto){
        Cart cart = new Cart();
        cart.setCartId(cartDto.getCartId());
        cart.setCustomerName(cartDto.getCustomerName());
        cart.setCardNumber(cartDto.getCardNumber());
        cart.setCartStatus(CartStatus.COMPLETED);
        return cart;
    }

    private CartDto toCartDto(Cart cart){
        CartDto cartDto = new CartDto();
        //List<CartProductDto> cartProductDtoList = new ArrayList<>();
        cartDto.setCartId(cart.getCartId());
        cartDto.setCustomerName(cart.getCustomerName());
        cartDto.setCardNumber(cart.getCardNumber());
        cartDto.setCartStatus(cart.getCartStatus());
        if(cart.getCartProductList() != null) {
            List<CartProductDto> cartProductDtoList = cart.getCartProductList().stream()
                    .map(cp -> {
                        CartProductDto cartProductDto = new CartProductDto();
                        cartProductDto.setCartProductId(cp.getCartProductId());
                        cartProductDto.setSalesQuantity(cp.getSalesQuantity());
                        cartProductDto.setProductId(cp.getProduct().getProductId());
                        cartProductDto.setCartId(cp.getCart().getCartId());
                        return cartProductDto;
                    })
                    .collect(Collectors.toList());
            cartDto.setCartProductDtoList(cartProductDtoList);
        }
        /*for(CartProduct cartProduct : cart.getCartProductList()){
            cartProductDtoList.add(toCartProductDto(cartProduct));
        }
        cartDto.setCartProductDtoList(cartProductDtoList);*/
        return cartDto;
    }

    private CartProduct toCartProductEntity(CartProductDto cartProductDto){
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCartProductId(cartProductDto.getCartProductId());
        cartProduct.setCart(findCart(cartProductDto.getCartId()));
        cartProduct.setProduct(findProduct(cartProductDto.getProductId()));
        cartProduct.setSalesQuantity(cartProductDto.getSalesQuantity());
        return cartProduct;
    }

    private CartProductDto toCartProductDto(CartProduct cartProduct){
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setCartProductId(cartProduct.getCartProductId());
        cartProductDto.setCartId(cartProduct.getCart().getCartId());
        cartProductDto.setProductId(cartProduct.getProduct().getProductId());
        cartProductDto.setSalesQuantity(cartProduct.getSalesQuantity());
        return cartProductDto;
    }

    private Product findProduct(long productId){
        Optional<Product> product = productRepository.findById(productId);
        return product.get();
    }

    private Cart findCart(long cartId){
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cart.get();
    }
}

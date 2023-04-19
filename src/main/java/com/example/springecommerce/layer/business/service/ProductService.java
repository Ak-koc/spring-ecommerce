package com.example.springecommerce.layer.business.service;

import com.example.springecommerce.layer.business.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto find(long productId);

    List<ProductDto> list(long categoryId);
}

package com.example.springecommerce.layer.presentation.rest;

import com.example.springecommerce.layer.business.dto.ProductDto;
import com.example.springecommerce.layer.business.service.ProductService;
import jakarta.persistence.ColumnResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/{id}")
    public ProductDto getProduct(@PathVariable("id") long productId){
        return productService.find(productId);
    }

    @GetMapping("/list/{category_id}")
    public List<ProductDto> getProductsByCategory(@PathVariable("category_id") long categoryId){
        return productService.list(categoryId);
    }
}

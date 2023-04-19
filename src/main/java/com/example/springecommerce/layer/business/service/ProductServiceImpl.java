package com.example.springecommerce.layer.business.service;

import com.example.springecommerce.layer.business.dto.ProductDto;
import com.example.springecommerce.layer.data.entity.Product;
import com.example.springecommerce.layer.data.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto find(long productId) {
        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isPresent()){
            return toDto(optional.get());
        }
        return null;
    }

    @Override
    public List<ProductDto> list(long categoryId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product : productRepository.findByCategoryId(categoryId)){
            productDtoList.add(toDto(product));
        }
        return productDtoList;
    }

    private ProductDto toDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setSalesPrice(product.getSalesPrice());
        productDto.setCategoryId(product.getCategory().getCategoryId());
        productDto.setCategoryName(product.getCategory().getCategoryName());
        return productDto;
    }
}

package com.example.springecommerce.layer.business.service;

import com.example.springecommerce.layer.business.dto.CategoryDto;
import com.example.springecommerce.layer.data.entity.Category;
import com.example.springecommerce.layer.data.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> list() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for(Category category : categoryRepository.findAll()){
            categoryDtoList.add(toDto(category));
        }
        return categoryDtoList;
    }

    private CategoryDto toDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }
}

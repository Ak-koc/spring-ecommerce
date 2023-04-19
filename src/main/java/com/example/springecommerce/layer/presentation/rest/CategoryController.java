package com.example.springecommerce.layer.presentation.rest;

import com.example.springecommerce.layer.business.dto.CategoryDto;
import com.example.springecommerce.layer.business.service.CategoryService;
import com.example.springecommerce.layer.business.service.CategoryServiceImpl;
import com.example.springecommerce.layer.data.entity.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public List<CategoryDto> getCategoryList(){
        return categoryService.list();
    }
}

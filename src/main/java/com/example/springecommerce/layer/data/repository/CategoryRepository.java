package com.example.springecommerce.layer.data.repository;

import com.example.springecommerce.layer.data.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}

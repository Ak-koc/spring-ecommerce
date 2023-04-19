package com.example.springecommerce.layer.data.entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    private String categoryName;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> getProductList;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getGetProductList() {
        return getProductList;
    }

    public void setGetProductList(List<Product> getProductList) {
        this.getProductList = getProductList;
    }
}

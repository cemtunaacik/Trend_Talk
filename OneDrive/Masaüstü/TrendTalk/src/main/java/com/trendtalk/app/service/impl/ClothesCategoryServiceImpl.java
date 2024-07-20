package com.trendtalk.app.service.impl;

import com.trendtalk.app.repository.interfaces.IClothesCategory;
import com.trendtalk.app.repository.entity.ClothesCategory;
import com.trendtalk.app.service.interfaces.IClothesCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothesCategoryServiceImpl implements IClothesCategoryService {

    private final IClothesCategory categoryRepository;

    @Autowired
    public ClothesCategoryServiceImpl(IClothesCategory categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ClothesCategory addCategory(ClothesCategory category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        return categoryRepository.save(category);
    }

    @Override
    public ClothesCategory updateCategory(Integer id, ClothesCategory category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<ClothesCategory> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<ClothesCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
}

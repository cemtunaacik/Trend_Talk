package com.trendtalk.app.service.interfaces;

import com.trendtalk.app.repository.entity.ClothesCategory;
import java.util.List;
import java.util.Optional;

public interface IClothesCategoryService {
    ClothesCategory addCategory(ClothesCategory category);
    ClothesCategory updateCategory(Integer id, ClothesCategory category);
    void deleteCategory(Integer id);
    Optional<ClothesCategory> getCategoryById(Integer id);
    List<ClothesCategory> getAllCategories();
}

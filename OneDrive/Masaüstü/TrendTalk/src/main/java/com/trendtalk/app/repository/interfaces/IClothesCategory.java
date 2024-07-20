package com.trendtalk.app.repository.interfaces;

import com.trendtalk.app.repository.entity.ClothesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClothesCategory extends JpaRepository<ClothesCategory, Integer> {
}

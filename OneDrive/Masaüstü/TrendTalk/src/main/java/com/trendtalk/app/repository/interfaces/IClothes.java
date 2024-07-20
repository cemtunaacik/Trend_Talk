package com.trendtalk.app.repository.interfaces;

import com.trendtalk.app.repository.entity.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClothes extends JpaRepository<Clothes, Integer> {
    List<Clothes> findByUserId(Integer userId);
    List<Clothes> findByUserIdAndCategoryId(Integer userId, Integer categoryId);
}

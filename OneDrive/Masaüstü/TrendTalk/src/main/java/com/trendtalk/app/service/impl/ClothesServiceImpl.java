package com.trendtalk.app.service.impl;

import com.trendtalk.app.repository.interfaces.IClothes;
import com.trendtalk.app.repository.entity.Clothes;
import com.trendtalk.app.service.interfaces.IClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesServiceImpl implements IClothesService {

    private final IClothes clothesRepository;

    @Autowired
    public ClothesServiceImpl(IClothes clothesRepository) {
        this.clothesRepository = clothesRepository;
    }

    @Override
    public Clothes addClothes(Clothes clothes) {
        return clothesRepository.save(clothes);
    }

    @Override
    public Clothes updateClothes(Integer id, Clothes clothes) {
        clothes.setId(id);
        return clothesRepository.save(clothes);
    }

    @Override
    public void deleteClothes(Integer id) {
        clothesRepository.deleteById(id);
    }

    @Override
    public List<Clothes> getClothesByUserId(Integer userId) {
        return clothesRepository.findByUserId(userId);
    }

    @Override
    public List<Clothes> getClothesByUserIdAndCategoryId(Integer userId, Integer categoryId) {
        return clothesRepository.findByUserIdAndCategoryId(userId, categoryId);
    }
}

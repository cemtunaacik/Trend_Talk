package com.trendtalk.app.service.interfaces;

import com.trendtalk.app.repository.entity.Clothes;
import java.util.List;

public interface IClothesService {
    Clothes addClothes(Clothes clothes);
    Clothes updateClothes(Integer id, Clothes clothes);
    void deleteClothes(Integer id);
    List<Clothes> getClothesByUserId(Integer userId);
    List<Clothes> getClothesByUserIdAndCategoryId(Integer userId, Integer categoryId);
}

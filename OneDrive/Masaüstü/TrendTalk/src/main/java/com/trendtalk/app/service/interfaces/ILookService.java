package com.trendtalk.app.service.interfaces;

import com.trendtalk.app.repository.entity.Look;

import java.util.List;

public interface ILookService {
    Look createLook(Look look);
    Look updateLook(Integer id, Look look);
    void deleteLook(Integer id);
    List<Look> getLooksByUserId(Integer userId);
}

package com.trendtalk.app.service.impl;

import com.trendtalk.app.repository.interfaces.ILook;
import com.trendtalk.app.repository.entity.Look;
import com.trendtalk.app.service.interfaces.ILookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookServiceImpl implements ILookService {

    private final ILook lookRepository;

    @Autowired
    public LookServiceImpl(ILook lookRepository) {
        this.lookRepository = lookRepository;
    }

    @Override
    public Look createLook(Look look) {
        return lookRepository.save(look);
    }

    @Override
    public Look updateLook(Integer id, Look look) {
        return lookRepository.save(look);
    }

    @Override
    public void deleteLook(Integer id) {
        lookRepository.deleteById(id);
    }

    public List<Look> getLooksByUserId(Integer userId) {
        return lookRepository.findByUserId(userId);
    }
}

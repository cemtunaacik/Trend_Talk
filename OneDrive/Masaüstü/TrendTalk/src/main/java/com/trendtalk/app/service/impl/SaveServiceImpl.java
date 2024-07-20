package com.trendtalk.app.service.impl;

import com.trendtalk.app.repository.interfaces.ISave;
import com.trendtalk.app.repository.entity.Save;
import com.trendtalk.app.service.interfaces.ISaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveServiceImpl implements ISaveService {

    private final ISave saveRepository;

    @Autowired
    public SaveServiceImpl(ISave saveRepository) {
        this.saveRepository = saveRepository;
    }

    @Override
    public Save savePost(Save save) {
        return saveRepository.save(save);
    }

    @Override
    public void unsavePost(Integer saveId) {
        saveRepository.deleteById(saveId);
    }
}

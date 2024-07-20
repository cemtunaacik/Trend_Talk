package com.trendtalk.app.service.interfaces;

import com.trendtalk.app.repository.entity.Save;

public interface ISaveService {
    Save savePost(Save save);
    void unsavePost(Integer saveId);

}

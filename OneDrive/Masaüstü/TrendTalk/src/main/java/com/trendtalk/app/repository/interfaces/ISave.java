package com.trendtalk.app.repository.interfaces;

import com.trendtalk.app.repository.entity.Save;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISave extends JpaRepository<Save, Integer> {
}

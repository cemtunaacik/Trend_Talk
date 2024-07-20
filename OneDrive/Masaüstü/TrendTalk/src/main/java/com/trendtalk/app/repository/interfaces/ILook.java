package com.trendtalk.app.repository.interfaces;

import com.trendtalk.app.repository.entity.Look;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILook extends JpaRepository<Look, Integer> {
    List<Look> findByUserId(Integer userId);
}

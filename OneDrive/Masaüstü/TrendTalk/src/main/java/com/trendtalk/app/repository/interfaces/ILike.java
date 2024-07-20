package com.trendtalk.app.repository.interfaces;

import com.trendtalk.app.repository.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ILike extends JpaRepository<Like, Integer> {
    List<Like> findByPostId(Integer postId);

    @Query("SELECT l FROM Like l WHERE l.user.id = :userId")
    List<Like> findByUserId(Integer userId);
}

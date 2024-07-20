package com.trendtalk.app.repository.interfaces;

import com.trendtalk.app.repository.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IPost extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findAllPostsByUserId(Integer userId);
    @Query("SELECT p FROM Post p WHERE p.user.id IN (SELECT f.id FROM User u JOIN u.following f WHERE u.id = :userId) ORDER BY p.dateTime DESC")
    List<Post> findFollowingUsersPosts(Integer userId);
    List<Post> findByDateTimeAfter(LocalDateTime dateTime);
    @Query("SELECT p FROM Post p ORDER BY RANDOM()")
    List<Post> findRandomPosts();
}

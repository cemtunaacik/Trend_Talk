package com.trendtalk.app.repository.interfaces;

import com.trendtalk.app.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUser extends JpaRepository<User, Integer> {
    Optional<User> findByNickname(String nickname);
    @Query("SELECT u.following FROM User u WHERE u.id = :userId")
    List<User> findFollowingByUserId(Integer userId);

    @Query("SELECT u.followers FROM User u WHERE u.id = :userId")
    List<User> findFollowersByUserId(Integer userId);
}

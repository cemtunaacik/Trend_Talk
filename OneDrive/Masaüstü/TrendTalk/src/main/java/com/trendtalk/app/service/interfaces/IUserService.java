package com.trendtalk.app.service.interfaces;

import com.trendtalk.app.repository.entity.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(User user);
    Optional<User> getUserById(Integer id);
    List<User> getAllUsers();
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
    Optional<User> findByNickname(String nickname);
    void followUser(Integer userId, Integer followUserId);
    void unfollowUser(Integer userId, Integer unfollowUserId);
    List<User> getFollowingByUserId(Integer userId);
    List<User> getFollowersByUserId(Integer userId);
    void updateProfilePicture(Integer userId, String profilePictureUrl);
}

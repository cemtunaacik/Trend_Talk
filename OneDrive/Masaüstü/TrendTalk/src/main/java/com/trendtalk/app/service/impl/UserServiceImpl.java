package com.trendtalk.app.service.impl;

import com.trendtalk.app.repository.entity.User;
import com.trendtalk.app.repository.interfaces.IUser;
import com.trendtalk.app.service.interfaces.IUserService;
import com.trendtalk.app.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUser userRepository;
    private final ILikeService likeService;

    @Autowired
    public UserServiceImpl(IUser userRepository, ILikeService likeService) {
        this.userRepository = userRepository;
        this.likeService = likeService;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> u.setLikes(likeService.getLikesByUserId(id)));
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Integer id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setNickname(user.getNickname());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public void followUser(Integer userId, Integer followUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        User followUser = userRepository.findById(followUserId)
                .orElseThrow(() -> new RuntimeException("User to follow not found!"));

        user.getFollowing().add(followUser);
        followUser.getFollowers().add(user);

        userRepository.save(user);
        userRepository.save(followUser);
    }

    @Override
    public void unfollowUser(Integer userId, Integer unfollowUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        User unfollowUser = userRepository.findById(unfollowUserId)
                .orElseThrow(() -> new RuntimeException("User to unfollow not found!"));

        user.getFollowing().remove(unfollowUser);
        unfollowUser.getFollowers().remove(user);

        userRepository.save(user);
        userRepository.save(unfollowUser);
    }

    @Override
    public List<User> getFollowingByUserId(Integer userId) {
        return userRepository.findFollowingByUserId(userId);
    }

    @Override
    public List<User> getFollowersByUserId(Integer userId) {
        return userRepository.findFollowersByUserId(userId);
    }

    @Override
    public void updateProfilePicture(Integer userId, String profilePictureUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setProfilePictureUrl(profilePictureUrl);
        userRepository.save(user);
    }
}

package com.trendtalk.app.service.impl;

import com.trendtalk.app.repository.entity.Post;
import com.trendtalk.app.repository.entity.User;
import com.trendtalk.app.repository.interfaces.IPost;
import com.trendtalk.app.repository.interfaces.IUser;
import com.trendtalk.app.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    private final IPost postRepository;
    private final IUser userRepository;

    @Autowired
    public PostServiceImpl(IPost postRepository, IUser userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post createPost(Integer userId, Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        post.setUser(user);
        post.setDateTime(LocalDateTime.now());
        Post savedPost = postRepository.save(post);

        user.setPostCount(user.getPosts().size());
        userRepository.save(user);

        return savedPost;
    }

    @Override
    public List<Post> getPostsByUserId(Integer userId) {
        return postRepository.findAllPostsByUserId(userId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(Integer id, Post postDetails) {
        return postRepository.findById(id).map(post -> {
            post.setImageUrl(postDetails.getImageUrl());
            post.setDescription(postDetails.getDescription());
            post.setDateTime(postDetails.getDateTime());
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
        User user = post.getUser();
        postRepository.delete(post);

        user.setPostCount(user.getPosts().size());
        userRepository.save(user);
    }

    @Override
    public List<Post> getRecentPosts() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        return postRepository.findByDateTimeAfter(sevenDaysAgo);
    }

    @Override
    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getFollowingUsersPosts(Integer userId) {
        List<Post> posts = postRepository.findFollowingUsersPosts(userId);
        for (Post post : posts) {
            post.setUserId(post.getUser().getId());
            post.setUsername(post.getUser().getNickname());
            post.setProfilePictureUrl(post.getUser().getProfilePictureUrl());
        }
        return posts;
    }

    @Override
    public List<Post> getRandomPosts() {
        return postRepository.findRandomPosts();
    }

    @Override
    public void updatePostImageUrl(Integer postId, String imageUrl) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setImageUrl(imageUrl);
        postRepository.save(post);
    }
}

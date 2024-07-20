package com.trendtalk.app.service.interfaces;

import com.trendtalk.app.repository.entity.Post;
import java.util.List;
import java.util.Optional;

public interface IPostService {
    Post createPost(Integer userId, Post post);
    List<Post> getPostsByUserId(Integer userId);
    List<Post> getAllPosts();
    Post updatePost(Integer id, Post post);
    void deletePost(Integer id);
    List<Post> getRecentPosts();
    Optional<Post> getPostById(Integer id);
    List<Post> getFollowingUsersPosts(Integer userId);
    List<Post> getRandomPosts();
    void updatePostImageUrl(Integer postId, String imageUrl);
}

package com.trendtalk.app.service.interfaces;

import com.trendtalk.app.repository.entity.Like;
import com.trendtalk.app.repository.entity.Post;
import java.util.List;
import java.util.Set;

public interface ILikeService {
    Like likePost(Integer userId, Integer postId);
    void unlikePost(Integer userId, Integer postId);
    List<Like> getLikesByPostId(Integer postId);
    Set<Post> getLikesByUserId(Integer userId);
}

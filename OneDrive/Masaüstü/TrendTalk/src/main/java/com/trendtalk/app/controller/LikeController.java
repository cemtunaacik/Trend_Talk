package com.trendtalk.app.controller;

import com.trendtalk.app.repository.entity.Like;
import com.trendtalk.app.repository.entity.Post;
import com.trendtalk.app.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final ILikeService likeService;

    @Autowired
    public LikeController(ILikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Like> likePost(@RequestParam("userId") Integer userId, @RequestParam("postId") Integer postId) {
        Like like = likeService.likePost(userId, postId);
        return ResponseEntity.ok(like);
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikePost(@RequestParam("userId") Integer userId, @RequestParam("postId") Integer postId) {
        likeService.unlikePost(userId, postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Like>> getLikesByPostId(@PathVariable Integer postId) {
        List<Like> likes = likeService.getLikesByPostId(postId);
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<Post>> getLikesByUserId(@PathVariable Integer userId) {
        Set<Post> likedPosts = likeService.getLikesByUserId(userId);
        return ResponseEntity.ok(likedPosts);
    }
}

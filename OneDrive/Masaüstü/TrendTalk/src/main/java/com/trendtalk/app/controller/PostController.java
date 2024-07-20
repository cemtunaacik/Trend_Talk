package com.trendtalk.app.controller;

import com.trendtalk.app.repository.entity.Post;
import com.trendtalk.app.repository.interfaces.IUser;
import com.trendtalk.app.service.interfaces.IFileStorageService;
import com.trendtalk.app.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final IPostService postService;
    private final IUser userRepository;
    private final IFileStorageService fileStorageService;

    @Autowired
    public PostController(IPostService postService, IUser userRepository, IFileStorageService fileStorageService) {
        this.postService = postService;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam("file") MultipartFile file,
                                           @RequestParam Integer userId,
                                           @RequestParam String description) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        Post post = new Post();
        post.setImageUrl(fileDownloadUri);
        post.setDescription(description);
        Post savedPost = postService.createPost(userId, post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id,
                                           @RequestParam("file") MultipartFile file,
                                           @RequestParam String description) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        Post post = new Post();
        post.setImageUrl(fileDownloadUri);
        post.setDescription(description);
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Integer userId) {
        List<Post> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<Post>> getFollowingUsersPosts(@PathVariable Integer userId) {
        List<Post> posts = postService.getFollowingUsersPosts(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/random")
    public ResponseEntity<List<Post>> getRandomPosts() {
        List<Post> posts = postService.getRandomPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Void> updatePostImage(@PathVariable Integer id, @RequestParam("url") String imageUrl) {
        postService.updatePostImageUrl(id, imageUrl);
        return ResponseEntity.ok().build();
    }
}

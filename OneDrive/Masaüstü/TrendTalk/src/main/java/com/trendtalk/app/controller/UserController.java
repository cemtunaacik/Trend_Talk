package com.trendtalk.app.controller;

import com.trendtalk.app.repository.entity.User;
import com.trendtalk.app.service.interfaces.IFileStorageService;
import com.trendtalk.app.service.interfaces.IUserService;
import com.trendtalk.app.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    private final IFileStorageService fileStorageService;
    private final ILikeService likeService;

    @Autowired
    public UserController(IUserService userService, IFileStorageService fileStorageService, ILikeService likeService) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam("nickname") String nickname,
                                           @RequestParam("name") String name,
                                           @RequestParam("surname") String surname,
                                           @RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("profilePicture") MultipartFile profilePicture) {

        String fileName = fileStorageService.storeFile(profilePicture);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        User user = new User();
        user.setNickname(nickname);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setProfilePictureUrl(fileDownloadUri);

        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(user -> {
                    user.setLikes(likeService.getLikesByUserId(id));
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id,
                                           @RequestParam("file") MultipartFile file,
                                           @RequestParam String nickname,
                                           @RequestParam String name,
                                           @RequestParam String surname,
                                           @RequestParam String email,
                                           @RequestParam String password) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        User user = new User();
        user.setNickname(nickname);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setProfilePictureUrl(fileDownloadUri);
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/follow/{followId}")
    public ResponseEntity<Void> followUser(@PathVariable Integer id, @PathVariable Integer followId) {
        userService.followUser(id, followId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/unfollow/{unfollowId}")
    public ResponseEntity<Void> unfollowUser(@PathVariable Integer id, @PathVariable Integer unfollowId) {
        userService.unfollowUser(id, unfollowId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<List<User>> getFollowing(@PathVariable Integer id) {
        List<User> following = userService.getFollowingByUserId(id);
        return ResponseEntity.ok(following);
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Integer id) {
        List<User> followers = userService.getFollowersByUserId(id);
        return ResponseEntity.ok(followers);
    }

    @PostMapping("/{id}/profile-picture")
    public ResponseEntity<Void> updateProfilePicture(@PathVariable Integer id, @RequestParam("url") String profilePictureUrl) {
        userService.updateProfilePicture(id, profilePictureUrl);
        return ResponseEntity.ok().build();
    }
}

package com.trendtalk.app.service.impl;

import com.trendtalk.app.repository.entity.Comment;
import com.trendtalk.app.repository.entity.Post;
import com.trendtalk.app.repository.entity.User;
import com.trendtalk.app.repository.interfaces.IComment;
import com.trendtalk.app.repository.interfaces.IPost;
import com.trendtalk.app.repository.interfaces.IUser;
import com.trendtalk.app.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    private final IComment commentRepository;
    private final IPost postRepository;
    private final IUser userRepository;

    @Autowired
    public CommentServiceImpl(IComment commentRepository, IPost postRepository, IUser userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Comment addCommentToPost(Integer postId, Comment comment, Integer userId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RuntimeException("Post not found with id " + postId));
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found with id " + userId));
        comment.setPost(post);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment updateComment(Integer id, Comment commentDetails) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setContent(commentDetails.getContent());
                    return commentRepository.save(comment);
                }).orElseThrow(() -> new RuntimeException("Comment not found with id " + id));
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}

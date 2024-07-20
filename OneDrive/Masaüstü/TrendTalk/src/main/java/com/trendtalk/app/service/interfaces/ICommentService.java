package com.trendtalk.app.service.interfaces;

import com.trendtalk.app.repository.entity.Comment;
import java.util.List;

public interface ICommentService {
    Comment addCommentToPost(Integer postId, Comment comment, Integer userId);
    List<Comment> getCommentsByPostId(Integer postId);
    Comment updateComment(Integer id, Comment comment);
    void deleteComment(Integer id);
}

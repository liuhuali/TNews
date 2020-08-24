package com.zr.service;

import com.zr.bean.Comment;

import java.util.List;

public interface CommentService {

    void save(Comment comment);

    List<Comment> findCommentByNewsId(Long newsId);
}

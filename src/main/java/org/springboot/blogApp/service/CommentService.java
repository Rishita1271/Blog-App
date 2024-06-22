package org.springboot.blogApp.service;

import org.springboot.blogApp.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}

package org.springboot.blogApp.service;

import org.springboot.blogApp.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long id);

    CommentDto getCommentById(long postId, long id);

    CommentDto updateComment(long postId, long id, CommentDto commentDto);
}

package org.springboot.blogApp.service.impl;

import org.springboot.blogApp.dto.CommentDto;
import org.springboot.blogApp.entity.Comment;
import org.springboot.blogApp.entity.Post;
import org.springboot.blogApp.exception.ResourceNotFoundException;
import org.springboot.blogApp.repository.CommentRepository;
import org.springboot.blogApp.repository.PostRepository;
import org.springboot.blogApp.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        //retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post ", "id:", postId));

        //link that post to our comment
        comment.setPost(post);

        //save that comment to db
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}

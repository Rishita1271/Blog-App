package org.springboot.blogApp.service;

import org.springboot.blogApp.dto.PostDto;

import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);
}

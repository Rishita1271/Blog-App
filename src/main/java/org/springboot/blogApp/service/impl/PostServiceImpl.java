package org.springboot.blogApp.service.impl;

import org.modelmapper.ModelMapper;
import org.springboot.blogApp.dto.PostDto;
import org.springboot.blogApp.entity.Post;
import org.springboot.blogApp.repository.PostRepository;
import org.springboot.blogApp.service.PostService;
import org.springframework.stereotype.Service;
import org.springboot.blogApp.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    private Post mapDtoToEntity(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }

    private PostDto mapEntityToDto(Post post) {
        PostDto postResource = mapper.map(post, PostDto.class);
//        postResource.setId(post.getId());
//        postResource.setTitle(post.getTitle());
//        postResource.setDescription(post.getDescription());
//        postResource.setContent(post.getContent());
        return postResource;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert DTO to entity
        Post post = mapDtoToEntity(postDto);
        Post newPost = postRepository.save(post);
        //convert entity to DTO
        PostDto postResource = mapEntityToDto(newPost);
        return postResource;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id));
        return mapEntityToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapEntityToDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id));
        postRepository.deleteById(id);
    }
}

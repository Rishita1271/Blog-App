package org.springboot.blogApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;

    //title should not be null or empty
    //title should be atleast 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have atleast 2 characters.")
    private String title;

    //description should not be null or empty
    //description should be atleast 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have atleast 2 characters.")
    private String description;

    //content should not be null or empty
    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
}
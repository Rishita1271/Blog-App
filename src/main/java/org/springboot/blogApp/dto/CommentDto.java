package org.springboot.blogApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Name should not be empty or null.")
    private String name;

    @NotEmpty(message = "Email should not be empty or null.")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Comment body must be atleast 10 characters.")
    private String body;
}
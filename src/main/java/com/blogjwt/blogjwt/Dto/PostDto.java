package com.blogjwt.blogjwt.Dto;

import com.blogjwt.blogjwt.Entity.Category;
import com.blogjwt.blogjwt.Entity.Comment;
import com.blogjwt.blogjwt.Entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long postId;
    @NotBlank
    @Size(min = 5)
    private String postTitle;
    private String postContent;
    private byte[] postImage;
    private Date postCreatedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();

}

package com.blogjwt.blogjwt.Service;

import com.blogjwt.blogjwt.Dto.PostDto;
import com.blogjwt.blogjwt.Utils.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    PostDto updatePost(PostDto postDto, Long postId);

    PostDto getPostById(Long postId);

    void deletePost(Long postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

    List<PostDto> getAllPostByCategory(Long categoryId);

    List<PostDto> getAllPostByUser(Long userId);


    /*List<PostDto> searchPostByKeyword(String keyword);
    List<PostDto> search(String keyword);*/
}

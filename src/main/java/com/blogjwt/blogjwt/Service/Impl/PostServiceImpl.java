package com.blogjwt.blogjwt.Service.Impl;

import com.blogjwt.blogjwt.Dto.PostDto;
import com.blogjwt.blogjwt.Entity.Category;
import com.blogjwt.blogjwt.Entity.Post;
import com.blogjwt.blogjwt.Entity.User;
import com.blogjwt.blogjwt.Exception.ResourcesNotFoundException;
import com.blogjwt.blogjwt.Repository.CategoryRepository;
import com.blogjwt.blogjwt.Repository.PostRepository;
import com.blogjwt.blogjwt.Repository.UserRepository;
import com.blogjwt.blogjwt.Service.PostService;
import com.blogjwt.blogjwt.Utils.PostResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private static final Logger LOGGER = Logger.getLogger(PostServiceImpl.class.getName());


    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found with ", "userId", userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourcesNotFoundException("Category not found with ", "categoryId", categoryId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setPostCreatedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        post.setPostImage(postDto.getPostImage());
        LOGGER.info("Post created successfully");
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourcesNotFoundException("Post not found with ", "postId", postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostCreatedDate(new Date());
        post.setPostContent(postDto.getPostContent());
        post.setPostImage(postDto.getPostImage());
        Post saved = this.postRepository.save(post);
        return this.modelMapper.map(saved, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourcesNotFoundException("Post not found with ", "postId", postId));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourcesNotFoundException("Post not found with ", "postId", postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();

        List<PostDto> postDtos = this.postRepository.findAll()
                .stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> postPage = this.postRepository.findAll(pageable);
        List<Post> content = postPage.getContent();

        return pagePostResponse(postPage, postDtos);
    }

    @Override
    public List<PostDto> getAllPostByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourcesNotFoundException("Category not found with ", "categoryId", categoryId));

        List<Post> posts = this.postRepository.findByCategory(category);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getAllPostByUser(Long userId) {
        return null;
    }

    private PostResponse pagePostResponse(Page<Post> postPage, List<PostDto> postDtos) {
        return PostResponse.builder()
                .content(postDtos)
                .pageNumber(postPage.getNumber())
                .totalPages(postPage.getTotalPages())
                .totalElements(postPage.getTotalElements())
                .pageSize(postPage.getSize())
                .IslastPage(postPage.isLast())
                .build();
    }
}

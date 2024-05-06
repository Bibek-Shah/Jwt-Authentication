package com.blogjwt.blogjwt.Service.Impl;

import com.blogjwt.blogjwt.Dto.CommentDto;
import com.blogjwt.blogjwt.Entity.Comment;
import com.blogjwt.blogjwt.Entity.Post;
import com.blogjwt.blogjwt.Exception.ResourcesNotFoundException;
import com.blogjwt.blogjwt.Repository.CommentRepository;
import com.blogjwt.blogjwt.Repository.PostRepository;
import com.blogjwt.blogjwt.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourcesNotFoundException("Post Not Found with", "postId", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment saved = this.commentRepository.save(comment);
        return this.modelMapper.map(saved, CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {

        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourcesNotFoundException("Comment Not Found with", "commentId", commentId));
        this.commentRepository.delete(comment);

    }
}

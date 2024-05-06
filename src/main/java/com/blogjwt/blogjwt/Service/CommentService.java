package com.blogjwt.blogjwt.Service;

import com.blogjwt.blogjwt.Dto.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId);

    void deleteComment(Long commentId);
}

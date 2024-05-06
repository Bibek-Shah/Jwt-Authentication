package com.blogjwt.blogjwt.Controller;

import com.blogjwt.blogjwt.Dto.CommentDto;
import com.blogjwt.blogjwt.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Long postId) {

        CommentDto createComment = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
    }
}

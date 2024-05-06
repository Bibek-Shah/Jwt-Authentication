package com.blogjwt.blogjwt.Repository;

import com.blogjwt.blogjwt.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

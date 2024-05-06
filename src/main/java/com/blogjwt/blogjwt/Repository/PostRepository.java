package com.blogjwt.blogjwt.Repository;

import com.blogjwt.blogjwt.Entity.Category;
import com.blogjwt.blogjwt.Entity.Post;
import com.blogjwt.blogjwt.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByPostTitle(String keyword);

    List<Post> findByPostTitleContaining(String keyword);
}

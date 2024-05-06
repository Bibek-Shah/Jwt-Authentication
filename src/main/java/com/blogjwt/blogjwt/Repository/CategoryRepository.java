package com.blogjwt.blogjwt.Repository;

import com.blogjwt.blogjwt.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

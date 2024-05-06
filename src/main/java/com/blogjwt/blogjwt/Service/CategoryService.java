package com.blogjwt.blogjwt.Service;

import com.blogjwt.blogjwt.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    void deleteCategory(Long categoryId);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategory();
}

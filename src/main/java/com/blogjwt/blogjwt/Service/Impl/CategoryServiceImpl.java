package com.blogjwt.blogjwt.Service.Impl;

import com.blogjwt.blogjwt.Dto.CategoryDto;
import com.blogjwt.blogjwt.Entity.Category;
import com.blogjwt.blogjwt.Exception.ResourcesNotFoundException;
import com.blogjwt.blogjwt.Repository.CategoryRepository;
import com.blogjwt.blogjwt.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saved = categoryRepository.save(category);
        return modelMapper.map(saved, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourcesNotFoundException("Category not found With :", "categoryId", categoryId));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category saved = categoryRepository.save(category);
        return this.modelMapper.map(saved, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourcesNotFoundException("Category not found With :", "categoryId", categoryId));
        categoryRepository.delete(category);

    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourcesNotFoundException("Category not found With :", "categoryId", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
    }
}

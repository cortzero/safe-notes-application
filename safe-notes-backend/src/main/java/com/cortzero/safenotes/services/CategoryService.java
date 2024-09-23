package com.cortzero.safenotes.services;

import com.cortzero.safenotes.dtos.CategoryDTO;
import com.cortzero.safenotes.mappers.CategoryMapper;
import com.cortzero.safenotes.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Long createCategory(CategoryDTO dto) {
        return 0L;
    }

    @Override
    public void deleteCategory(Long categoryId) {

    }

    @Override
    public void deleteCategoryByName(String categoryName) {

    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        return null;
    }

    @Override
    public CategoryDTO getCategoryByName(String categoryName) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::getDTO)
                .toList();
    }

}

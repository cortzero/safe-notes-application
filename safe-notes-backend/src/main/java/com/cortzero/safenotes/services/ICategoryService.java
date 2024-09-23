package com.cortzero.safenotes.services;

import com.cortzero.safenotes.dtos.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    Long createCategory(CategoryDTO dto);
    void deleteCategory(Long categoryId);
    void deleteCategoryByName(String categoryName);
    CategoryDTO getCategoryById(Long categoryId);
    CategoryDTO getCategoryByName(String categoryName);
    List<CategoryDTO> getAllCategories();

}

package com.cortzero.safenotes.mappers;

import com.cortzero.safenotes.dtos.CategoryDTO;
import com.cortzero.safenotes.entities.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category getEntity(CategoryDTO dto) {
        // TODO: Implement the mapping from dto to entity
        return null;
    }

    public CategoryDTO getDTO(Category category) {
        if (category == null)
            throw new IllegalArgumentException("The category must not be null");
        return CategoryDTO.builder().name(category.getName()).build();
    }

}

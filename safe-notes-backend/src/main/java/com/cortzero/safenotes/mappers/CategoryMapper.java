package com.cortzero.safenotes.mappers;

import com.cortzero.safenotes.dtos.CategoryDTO;
import com.cortzero.safenotes.entities.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category getEntity(CategoryDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("The dto must not be null");
        return Category.builder().id(dto.getId()).name(dto.getName()).build();
    }

    public CategoryDTO getDTO(Category category) {
        if (category == null)
            throw new IllegalArgumentException("The category must not be null");
        return CategoryDTO.builder().id(category.getId()).name(category.getName()).build();
    }

}

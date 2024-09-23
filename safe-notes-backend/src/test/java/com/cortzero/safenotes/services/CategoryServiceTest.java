package com.cortzero.safenotes.services;

import com.cortzero.safenotes.dtos.CategoryDTO;
import com.cortzero.safenotes.entities.Category;
import com.cortzero.safenotes.mappers.CategoryMapper;
import com.cortzero.safenotes.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void CategoryService_GetAllCategories_ReturnsAllCategoriesDto() {
        // Given
        Category category1 = Category.builder().id(1L).name("school").build();
        Category category2 = Category.builder().id(2L).name("work").build();
        Category category3 = Category.builder().id(3L).name("social").build();

        CategoryDTO categoryDTO1 = CategoryDTO.builder().name("school").build();
        CategoryDTO categoryDTO2 = CategoryDTO.builder().name("work").build();
        CategoryDTO categoryDTO3 = CategoryDTO.builder().name("social").build();

        List<Category> categoryList = new ArrayList<>(List.of(category1, category2, category3));

        when(categoryRepository.findAll()).thenReturn(categoryList);
        when(categoryMapper.getDTO(any(Category.class))).thenReturn(categoryDTO1, categoryDTO2, categoryDTO3);

        // When
        List<CategoryDTO> returnedDTOs = categoryService.getAllCategories();

        // Then
        assertThat(returnedDTOs).isNotNull();
        assertThat(returnedDTOs.size()).isEqualTo(3);
        assertThat(returnedDTOs).containsExactly(categoryDTO1, categoryDTO2, categoryDTO3);

        verify(categoryRepository, times(1)).findAll();
        verify(categoryMapper, times(categoryList.size())).getDTO(any(Category.class));
    }

}

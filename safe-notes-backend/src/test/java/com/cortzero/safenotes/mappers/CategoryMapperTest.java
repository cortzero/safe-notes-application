package com.cortzero.safenotes.mappers;

import com.cortzero.safenotes.dtos.CategoryDTO;
import com.cortzero.safenotes.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CategoryMapperTest {

    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        categoryMapper = new CategoryMapper();
    }

    @Test
    public void CategoryMapper_getEntity_ReturnsCategoryEntity() {
        // Given
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("school").build();

        // When
        Category category = categoryMapper.getEntity(categoryDTO);

        // Then
        assertThat(category).isNotNull();
        assertThat(category.getName()).isEqualTo(categoryDTO.getName());
    }

    @Test
    public void CategoryMapper_getEntity_ThrowsIllegalArgumentException_WhenDtoIsNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> categoryMapper.getEntity(null))
                .withMessage("The dto must not be null");
    }

    @Test
    public void CategoryMapper_getDTO_ReturnsCategoryDto() {
        // Given
        Category category = Category.builder().id(1L).name("school").build();

        // When
        CategoryDTO categoryDTO = categoryMapper.getDTO(category);

        // Then
        assertThat(categoryDTO).isNotNull();
        assertThat(categoryDTO.getName()).isEqualTo(category.getName());
    }

    @Test
    public void CategoryMapper_getDTO_ThrowsIllegalArgumentException_WhenCategoryIsNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> categoryMapper.getDTO(null))
                .withMessage("The category must not be null");
    }

}

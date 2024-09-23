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

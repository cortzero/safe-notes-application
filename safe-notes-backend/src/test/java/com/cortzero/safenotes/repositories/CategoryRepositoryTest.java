package com.cortzero.safenotes.repositories;

import static org.assertj.core.api.Assertions.*;

import com.cortzero.safenotes.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void CategoryRepository_FindAll_ReturnsListOfCategories() {
        // Given
        Category category1 = Category.builder().name("school").build();
        Category category2 = Category.builder().name("work").build();
        Category category3 = Category.builder().name("social").build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        // When
        List<Category> foundCategories = categoryRepository.findAll();

        // Then
        assertThat(foundCategories).isNotNull();
        assertThat(foundCategories.size()).isEqualTo(3);
        assertThat(foundCategories).containsExactlyInAnyOrder(category1, category2, category3);
    }

    @Test
    public void CategoryRepository_FindByName_ReturnsTheCategoryWithTheRequestedName() {
        // Given
        Category category1 = Category.builder().name("school").build();
        Category category2 = Category.builder().name("work").build();
        Category category3 = Category.builder().name("social").build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        // When
        Optional<Category> foundCategory = categoryRepository.findByName("school");

        // Then
        assertThat(foundCategory).isPresent();
        assertThat(foundCategory.get()).isEqualTo(category1);
        assertThat(foundCategory.get().getName()).isEqualTo("school");
    }

    @Test
    public void CategoryRepository_FindByName_ReturnsEmptyOptional_WhenCategoryDoesNotExist() {
        // Given
        Category category1 = Category.builder().name("school").build();
        Category category2 = Category.builder().name("work").build();
        Category category3 = Category.builder().name("social").build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        // When
        Optional<Category> foundCategory = categoryRepository.findByName("personal");

        // Then
        assertThat(foundCategory).isNotPresent();
    }

}

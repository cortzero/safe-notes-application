package com.cortzero.safenotes.controllers;

import com.cortzero.safenotes.dtos.CategoryDTO;
import com.cortzero.safenotes.services.CategoryService;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void CategoriesController_GetAllCategories_Returns200StatusCode_And_ListOfCategoriesDto() throws Exception {
        // Given
        CategoryDTO categoryDTO1 = CategoryDTO.builder().name("school").build();
        CategoryDTO categoryDTO2 = CategoryDTO.builder().name("work").build();
        CategoryDTO categoryDTO3 = CategoryDTO.builder().name("social").build();
        List<CategoryDTO> categoryDTOList = new ArrayList<>(List.of(categoryDTO1, categoryDTO2, categoryDTO3));
        when(categoryService.getAllCategories()).thenReturn(categoryDTOList);

        // When
        ResultActions response = mockMvc.perform(get("/api/categories"));

        // Then
        response.andExpect(status().isOk());

        DocumentContext documentContext = JsonPath.parse(response.andReturn().getResponse().getContentAsString());

        int numberOfCategoriesReturned = documentContext.read("$.length()");
        assertThat(numberOfCategoriesReturned).isEqualTo(3);

        JSONArray categoryNames = documentContext.read("$..name");
        assertThat(categoryNames).containsExactly("school", "work", "social");

        verify(categoryService, times(1)).getAllCategories();
    }

}

package org.example.springdatajpapractice.controller;


import org.example.springdatajpapractice.dtoExample.categoryDto.CategoryMapper;
import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.repository.CategoryRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
@WebMvcTest({CategoryController.class, CategoryMapper.class})
public class CategoryControllerMockTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CategoryRepository categoryRepository;

    @Test
    void findById_shouldReturnOk_whenCorrectIdGiven() throws Exception {
        Long categoryId = 1L;

        Category category = new Category();
        category.setId(categoryId);
        category.setName("Гаджеты");

        Mockito.when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));

        mockMvc.perform(get("/categories/{id}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(category.getId()))
                .andExpect(jsonPath("$.name").value(category.getName()));
    }

    @Test
    void findById_shouldReturnNotFound_whenIncorrectIdGiven() throws Exception {
        Long categoryId = 999L;

        Mockito.when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/categories/{id}", categoryId))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_shouldReturnNonEmptyList() throws Exception {
        Category category1 = new Category(1L, "Микрофоны");
        Category category2 = new Category(2L, "Акустические гитары");

        List<Category> categories = List.of(category1, category2);

        Mockito.when(categoryRepository.findAll(PageRequest.of(0, 5)))
                .thenReturn(new PageImpl<>(categories));

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(categories.size()))
                .andExpect(jsonPath("$[0].id").value(categories.get(0).getId()))
                .andExpect(jsonPath("$[0].name").value(categories.get(0).getName()))
                .andExpect(jsonPath("$[1].id").value(categories.get(1).getId()))
                .andExpect(jsonPath("$[1].name").value(categories.get(1).getName()));
    }

    @Test
    void findAll_shouldReturnEmptyList() throws Exception {
        Mockito.when(categoryRepository.findAll(PageRequest.of(0, 5)))
                .thenReturn(new PageImpl<>(List.of()));

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.empty()));
    }


}

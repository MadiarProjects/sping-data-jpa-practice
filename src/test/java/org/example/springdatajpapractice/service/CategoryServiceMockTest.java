package org.example.springdatajpapractice.service;

import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceMockTest {
    @Mock
    CategoryRepository categoryRepository;


    @InjectMocks
    CategoryService categoryService ;


    @Test
    void create_shouldNotCreate_whenDuplicateNameGiven() {
        String expectedMessage = "Категория с таким названием уже существует";


        Mockito.when(categoryRepository.existsByName("Клавиатуры"))
                .thenReturn(true);

        Category category = new Category();
        category.setName("Клавиатуры");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> categoryService.create(category));
        Assertions.assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    void create_shouldCreate_whenCorrectNameGiven() {
        Mockito.when(categoryRepository.existsByName("Клавиатуры"))
                .thenReturn(false);

        Category category = new Category();
        category.setName("Клавиатуры");

        Category savedCategory = categoryService.create(category);

        Assertions.assertEquals(category.getName(), savedCategory.getName());
    }
}

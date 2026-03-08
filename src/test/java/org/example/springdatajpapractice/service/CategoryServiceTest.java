package org.example.springdatajpapractice.service;

import org.example.springdatajpapractice.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    //интограционные тест
    //он использует уже существующий базу данных а этого не должно быть и изза этого оно не запускается снова
    //Moki test спасает от этого
    @Test
    void create_shouldCreate_whenCorrectNameGiven(){
        //1подготовка
        Category category=new Category();
        category.setName("Процессоры");

        //2выполнение
        Category savedCategory= categoryService.create(category);

        //3проверка
        Assertions.assertEquals(category.getName(),savedCategory.getName());
        Assertions.assertNotNull(savedCategory.getId());
    }

}

package org.example.springdatajpapractice.controller;

import org.example.springdatajpapractice.dtoExample.categoryDto.CategoryCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CategoryControllerTest {
    @Autowired
    MockMvc mockMvc; // http-клиент

    //   GET /categories/{id} - 200 ок
    //   GET /categories/{id} - 404 not found

    // mockMvc.perform(RequestBuilder) - отправка http-запроса и получение ответа
    // RequestBuilder - тип описывающий запрос (какой путь, метод, тело, заголовки и тд)

    // MockMvcRequestBuilders - утилитарный класс для получения реализаций RequestBuilder используя такие методы как:
    // - get(путь)
    // - post(путь)
    // - put(путь)

    // ResultActions - результат http ответа
    // resultActions.andExpect(ResultMatcher) - метод позволяющий проверить ответ
    // ResultMatcher - тип описывающий проверку http-ответа (статус, тело, заголовки)
    // MockMvcResultMatchers - утилитарный класс для получения реализаций ResultMatcher используя такие методы как:
    // - status()
    // - jsonPath(выражение)
    // $ - результат (объект, массив)
    // $.id - обращение к полю id объекта
    // $.price - обращение к полю price объекта
    // $.owner.id - обращение к полю id вложенного объекта owner

    // $[0] - обращение к первому элементу массива
    // $[0].id - обращение к полю id первого элемента массива

    @Test
    void findById_shouldReturnOk_whenCorrectIdGiven() throws Exception {
        int categoryId = 1;
        ResultActions resultActions = mockMvc.perform(get("/categories/" + categoryId));
        resultActions.andExpect(status().isOk()); // проверка, что статаус 200
        resultActions.andExpect(jsonPath("$.id").value(categoryId)); // проверка, что id равен 1
        resultActions.andExpect(jsonPath("$.name").value("Процессоры")); // проверка названия
    }

    @Test
    void findById_shouldReturnNotFound_whenCorrectIdGiven() throws Exception{
        int categoryId=99;
        mockMvc.perform(get("/categories"+categoryId))
                .andExpect(status().isNotFound());
    }


    @Autowired
    ObjectMapper objectMapper;    // jackson

    @Test
    void create_shouldReturnOk_WhenCorrectDataGiven() throws Exception {
        CategoryCreateDto createDto = new CategoryCreateDto();
        createDto.setName("Процессоры");

        String json = objectMapper.writeValueAsString(createDto);

        mockMvc.perform(post("/categories").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(createDto.getName()));
    }

    @Test
    void create_shouldBadRequest_WhenEmptyNameGiven() throws Exception {
        CategoryCreateDto createDto = new CategoryCreateDto();
        createDto.setName("");

        String json = objectMapper.writeValueAsString(createDto);

        mockMvc.perform(post("/categories").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
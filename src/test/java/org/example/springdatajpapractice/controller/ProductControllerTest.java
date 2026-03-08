package org.example.springdatajpapractice.controller;


import org.example.springdatajpapractice.dtoExample.categoryDto.CategoryCreateDto;
import org.example.springdatajpapractice.dtoExample.categoryDto.CategoryResponseDto;
import org.example.springdatajpapractice.model.Product;
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
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    void findById_shouldReturnOk_whenCorrectIdGiven() throws Exception {
//        Long productId = 1L;
//        mockMvc.perform(get("/products/" + productId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(productId))
//                .andExpect(jsonPath("$.name").value("Intel Core I9 9900"))
//                .andExpect(jsonPath("$.price").value(249990));
//    }
//
//    @Test
//    void findById_shouldReturnNotFound_whenCorrectIdGiven() throws Exception {
//        Long productId = 99L;
//        mockMvc.perform(get("/products/" + productId))
//                .andExpect(status().isNotFound());
//    }

    @Test
    void create_shouldReturnOk_WhenCorrectDataGiven() throws Exception {
        CategoryCreateDto createDto = new CategoryCreateDto();
        createDto.setName("Процессоры");

        String json = objectMapper.writeValueAsString(createDto);

        String responseJson = mockMvc.perform(post("/categories").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(createDto.getName()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        CategoryResponseDto category = objectMapper.readValue(responseJson, CategoryResponseDto.class);

        Product product = new Product();
        product.setName("Intel Core I9");
        product.setPrice(419990.0);

        json = objectMapper.writeValueAsString(product);
        mockMvc.perform(post("/products?categoryId=" + category.getId()).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.category.id").value(category.getId()))
                .andExpect(jsonPath("$.category.name").value(category.getName()));
    }
}

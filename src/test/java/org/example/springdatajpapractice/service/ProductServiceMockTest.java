package org.example.springdatajpapractice.service;

import org.checkerframework.checker.units.qual.C;
import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.model.Option;
import org.example.springdatajpapractice.model.Product;
import org.example.springdatajpapractice.repository.CategoryRepository;
import org.example.springdatajpapractice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceMockTest {
    //1 сценарий, товар создается
    //2 сценарий, товар не создается из-за неправильного id категорий
    @Mock
    ProductRepository productRepository;
    @Mock
    CategoryRepository categoryRepository;


    @InjectMocks
    ProductService productService;


    @Test
    void create_createWhenCategoryIdCorrect() {
        Category category=new Category();
        category.setName("гарнитура");

        Product toSaveProduct = new Product();
        toSaveProduct.setName("клавиатура");
        Long categoryId=1L;

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        Product savedProduct = productService.create(toSaveProduct, categoryId);
        Assertions.assertNotNull(savedProduct.getCategory());
    }

    @Test
    void create_notCreateWhenCategoryIdInCorrect() {
        Product toSaveProduct = new Product();
        toSaveProduct.setName("клавиатура");
        Long categoryId=1L;
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> productService.create(toSaveProduct, categoryId));
        Assertions.assertEquals("Категория не найдена", ex.getMessage());
    }
}

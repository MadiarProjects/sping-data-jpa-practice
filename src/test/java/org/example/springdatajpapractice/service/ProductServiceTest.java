package org.example.springdatajpapractice.service;


import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
public class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @Test
    public void deleteById_shouldNotBeDeleted_whenCorrectIdGiven(){
        Product product=new Product();
        product.setName("цепочка");
        Category category=new Category();
        category.setName("аксессуары");
        categoryService.create(category);
        product.setCategory(category);

        Product savedProduct= productService.create(product,category.getId());
        productService.deleteById(product.getId()+1);

        Assertions.assertDoesNotThrow(()->productService.findById(savedProduct.getId()));

    }

    @Test
    public void deleteById_shouldBeDeleted_whenCorrectIdGiven(){
        Product product=new Product();
        product.setName("цепочка");
        Category category=new Category();
        category.setName("аксессуары");
        categoryService.create(category);
        product.setCategory(category);

        Product savedProduct= productService.create(product,category.getId());
        productService.deleteById(product.getId());

        Assertions.assertThrows(RuntimeException.class,()->productService.findById(savedProduct.getId()));
    }
}

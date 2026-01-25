package org.example.springdatajpapractice.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.model.Product;
import org.example.springdatajpapractice.repository.CategoryRepository;
import org.example.springdatajpapractice.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @PostMapping
    public Product create(@RequestBody Product product,@RequestParam int categoryId ){
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow();
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
    @GetMapping
    public List<Product> findAll(@RequestParam(defaultValue = "0") Double min, @RequestParam(defaultValue = Integer.MAX_VALUE+"") Double max){
        return productRepository.findByPriceBetween(min,max);
    }
    @GetMapping("/{id}")
    public Product findById(@PathVariable int id){
        return productRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    public Product update(@RequestBody Product product,@PathVariable int id){
        Product foundProduct=productRepository.findById(id).orElseThrow();
        foundProduct.setName(product.getName());
        foundProduct.setPrice(product.getPrice());
        return productRepository.save(foundProduct);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        productRepository.deleteById(id);
    }
}

package org.example.springdatajpapractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.springdatajpapractice.repository.CategoryRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")

public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    @GetMapping("/{id}")
    public Category findById(@PathVariable int id){
        return categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public Category create(@RequestBody Category category){
         return categoryRepository.save(category);
    }
    @PutMapping("/{id}")
    public Category update(@RequestBody Category category,@PathVariable int id){
        Category foundCategory=categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        foundCategory.setName(category.getName());
        categoryRepository.save(foundCategory);
        return foundCategory;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        categoryRepository.deleteById(id);
    }
}

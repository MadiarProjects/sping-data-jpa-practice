package org.example.springdatajpapractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.dtoExample.categoryDto.CategoryCreateDto;
import org.example.springdatajpapractice.dtoExample.categoryDto.CategoryMapper;
import org.example.springdatajpapractice.dtoExample.categoryDto.CategoryResponseDto;
import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.model.Option;
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
    private final CategoryMapper categoryMapper;
    @GetMapping
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public CategoryResponseDto create(@RequestBody CategoryCreateDto categoryCreateDto){
        Category category=new Category();
        category.setName(categoryCreateDto.getName());

        categoryCreateDto.getOptions().forEach(name -> {
            Option option = new Option();
            option.setName(name);
            option.setCategory(category);
            category.getOptions().add(option);
        });
        categoryRepository.save(category);
        return categoryMapper.categoryCreate(category);

    }
    @PutMapping("/{id}")
    public Category update(@RequestBody Category category,@PathVariable Long id){
        Category foundCategory=categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        foundCategory.setName(category.getName());
        categoryRepository.save(foundCategory);
        return foundCategory;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryRepository.deleteById(id);
    }
}

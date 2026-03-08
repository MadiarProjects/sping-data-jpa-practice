package org.example.springdatajpapractice.service;

import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidParameterException;


@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Category create(Category category){
        if (category.getName()==null||category.getName().isBlank()){
            throw new InvalidParameterException("name should not be empty");
        }

        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Категория с таким названием уже существует");
        }
        categoryRepository.save(category);

        return category;
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
    public Category findById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("категория не найдена"));
    }
}

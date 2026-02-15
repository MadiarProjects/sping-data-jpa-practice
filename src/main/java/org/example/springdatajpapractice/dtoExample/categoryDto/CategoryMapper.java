package org.example.springdatajpapractice.dtoExample.categoryDto;


import org.example.springdatajpapractice.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponseDto categoryCreate(Category category){
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getOptions().stream()
                        .map(o -> new OptionShortDto(o.getId(), o.getName()))
                        .toList()
        );
    }
}

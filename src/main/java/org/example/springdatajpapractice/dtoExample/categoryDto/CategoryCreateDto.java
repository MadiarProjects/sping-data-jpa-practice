package org.example.springdatajpapractice.dtoExample.categoryDto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryCreateDto {
    private String name;
    private List<String> options;
}

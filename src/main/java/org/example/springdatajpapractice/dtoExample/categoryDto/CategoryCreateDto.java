package org.example.springdatajpapractice.dtoExample.categoryDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryCreateDto {
    @NotBlank
    private String name;
    private List<String> options=new ArrayList<>();
}

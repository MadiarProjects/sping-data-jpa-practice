package org.example.springdatajpapractice.dtoExample.categoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
    private List<OptionShortDto> options;
}

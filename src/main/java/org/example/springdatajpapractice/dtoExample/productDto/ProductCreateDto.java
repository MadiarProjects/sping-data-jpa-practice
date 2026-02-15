package org.example.springdatajpapractice.dtoExample.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductCreateDto {
    private String name;
    private Double price;
    private Long categoryId;
    private List<OptionAndValueCreateDto> optionIdsAndValueNames;
}

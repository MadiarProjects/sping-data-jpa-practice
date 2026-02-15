package org.example.springdatajpapractice.dtoExample.productDto;

import lombok.Data;

import java.util.List;

@Data
public class ProductUpdateDto {
    private String name;
    private Double price;
    private List<OptionAndValueCreateDto> optionAndValueCreateDto;
}

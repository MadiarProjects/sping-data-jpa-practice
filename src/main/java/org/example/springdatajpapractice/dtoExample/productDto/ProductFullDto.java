package org.example.springdatajpapractice.dtoExample.productDto;

import lombok.Data;

@Data
public class ProductFullDto {

    private Long id;
    private String name;
    private Double price;
    private String category;
    private ResponseOptionAndValueDto[] optionAndValueStrings;
}

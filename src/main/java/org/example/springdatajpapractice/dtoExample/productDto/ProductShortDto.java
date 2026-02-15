package org.example.springdatajpapractice.dtoExample.productDto;

import lombok.Data;

@Data
public class ProductShortDto {
    private Long id;
    private String name;
    private Double price;
    private String category;

}

package org.example.springdatajpapractice.dtoExample.productDto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProductFullDto {

    private Long id;
    private String name;
    private Double price;
    private String category;
    private Map <String,String> options=new HashMap<>();
}

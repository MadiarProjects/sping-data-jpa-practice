package org.example.springdatajpapractice.dtoExample.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.model.Option;
import org.example.springdatajpapractice.model.Value;

import java.nio.file.LinkOption;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ProductCreateDto {
    private String name;
    private Double price;
    private Long categoryId;
    private List<ValueShortDto> valueShortDto;
}

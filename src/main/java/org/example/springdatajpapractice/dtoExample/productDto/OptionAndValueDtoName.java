package org.example.springdatajpapractice.dtoExample.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionAndValueDtoName {
    private String optionName;
    private String valueName;
}

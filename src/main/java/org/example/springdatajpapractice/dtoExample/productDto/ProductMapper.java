package org.example.springdatajpapractice.dtoExample.productDto;

import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.model.Product;
import org.example.springdatajpapractice.model.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductShortDto toShortDto(Product product) {
        ProductShortDto productDto = new ProductShortDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }

    public ProductFullDto toFullDto(Product product) {
        ProductFullDto productFullDto = new ProductFullDto();
        productFullDto.setId(product.getId());
        productFullDto.setName(product.getName());
        productFullDto.setPrice(product.getPrice());
        productFullDto.setCategory(product.getCategory().getName());
        OptionAndValueDtoName[] optionAndValueStrings=new OptionAndValueDtoName[product.getValues().size()];
        for (int i = 0; i <product.getValues().size() ; i++) {
            Value value=product.getValues().get(i);
            optionAndValueStrings[i]=new OptionAndValueDtoName(value.getOption().getName(),value.getName());
        }
        productFullDto.setOptionAndValueStrings(optionAndValueStrings);
        return productFullDto;
    }
//    public Product productCreate(ProductCreateDto productCreateDto,Category category){
//
//        product.getValues().stream().forEach(value-> {
//            for (int i = 0; i < category.getOptions().size(); i++) {
//
//                value.setName(productCreateDto.getOptions().get(i));
//            }
//        });
//    }
//
}

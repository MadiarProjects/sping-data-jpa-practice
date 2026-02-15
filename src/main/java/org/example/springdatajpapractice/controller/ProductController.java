package org.example.springdatajpapractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.dtoExample.productDto.ProductCreateDto;
import org.example.springdatajpapractice.dtoExample.productDto.ProductFullDto;
import org.example.springdatajpapractice.dtoExample.productDto.ProductShortDto;
import org.example.springdatajpapractice.dtoExample.productDto.ProductMapper;
import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.model.Option;
import org.example.springdatajpapractice.model.Product;
import org.example.springdatajpapractice.model.Value;
import org.example.springdatajpapractice.repository.CategoryRepository;
import org.example.springdatajpapractice.repository.OptionRepository;
import org.example.springdatajpapractice.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    @PostMapping
    public ProductFullDto create(@RequestBody ProductCreateDto productCreateDto){
        Category category=categoryRepository.findById(productCreateDto.getCategoryId())
                .orElseThrow();
        Product product=new Product();
        product.setName(productCreateDto.getName());
        product.setPrice(productCreateDto.getPrice());
        product.setCategory(category);
        productCreateDto.getValueShortDto().forEach(valueShortDto->{
            Option option=category
                    .getOptions()
                    .stream()
                    .filter(o->o.getId().equals(valueShortDto.getOptionId()))
                    .findFirst()
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            Value value=new Value();
            value.setName(valueShortDto.getValueName());
            value.setOption(option);
            value.setProduct(product);
            product.getValues().add(value);
        });
        productRepository.save(product);
        return productMapper.toFullDto(product);
    }
    @GetMapping
    public List<ProductShortDto> findAll(@RequestParam(defaultValue = "0") Double min, @RequestParam(defaultValue = Integer.MAX_VALUE+"") Double max){
        return productRepository.findByPriceBetween(min,max).stream()
                .map(productMapper::toShortDto)
                .toList();
    }
    @GetMapping("/{id}")
    public ProductFullDto findById(@PathVariable int id){
        Product product= productRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        return productMapper.toFullDto(product);
    }
    @PutMapping("/{id}")
    public Product update(@RequestBody Product product,@PathVariable int id){
        Product foundProduct=productRepository.findById(id).orElseThrow();
        foundProduct.setName(product.getName());
        foundProduct.setPrice(product.getPrice());
        return productRepository.save(foundProduct);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        productRepository.deleteById(id);
    }
}

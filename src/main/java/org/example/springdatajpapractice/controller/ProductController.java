package org.example.springdatajpapractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.dtoExample.productDto.*;
import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.model.Option;
import org.example.springdatajpapractice.model.Product;
import org.example.springdatajpapractice.model.Value;
import org.example.springdatajpapractice.repository.CategoryRepository;
import org.example.springdatajpapractice.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @PostMapping
    public ProductFullDto create(@RequestBody ProductCreateDto productCreateDto) {
        Category category = categoryRepository.findById(productCreateDto.getCategoryId())
                .orElseThrow();
        Product product = new Product();
        product.setName(productCreateDto.getName());
        product.setPrice(productCreateDto.getPrice());
        product.setCategory(category);
        productCreateDto.getOptionIdsAndValueNames().forEach(valueShortDto -> {
            Option option = category
                    .getOptions()
                    .stream()
                    .filter(o -> o.getId().equals(valueShortDto.getOptionId()))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Value value = new Value();
            value.setName(valueShortDto.getValueName());
            value.setOption(option);
            value.setProduct(product);
            product.getValues().add(value);
        });
        productRepository.save(product);
        return productMapper.toFullDto(product);
    }

    @GetMapping
    public List<ProductShortDto> findAll(
            @RequestParam(defaultValue = "0") Double min,
            @RequestParam(defaultValue = Integer.MAX_VALUE + "") Double max,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false) String field
                                         ) {
        List<String> names=List.of("id","name","price");
        if (!names.contains(field)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (page<1||size<1 || size>100){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Sort sort=Sort.by(sortDirection,field);
        Pageable pageable= PageRequest.of(page-1,size,sort);
        return productRepository.findByPriceBetween(min, max,pageable).stream()
                .map(productMapper::toShortDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductFullDto findById(@PathVariable int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return productMapper.toFullDto(product);
    }

    @PutMapping("/{id}")
    public ProductFullDto update(@RequestBody ProductUpdateDto productUpdateDto, @PathVariable int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        product.setName(productUpdateDto.getName());
        product.setPrice(productUpdateDto.getPrice());
        productUpdateDto.getOptionAndValueCreateDto().forEach(optionAndValueCreateDto -> {
            product.getValues().forEach(value -> {
                if (value.getOption().getId().equals(optionAndValueCreateDto.getOptionId())) {
                    value.setName(optionAndValueCreateDto.getValueName());
                }
            });
        });
        productRepository.save(product);
        return productMapper.toFullDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productRepository.deleteById(id);
    }
}

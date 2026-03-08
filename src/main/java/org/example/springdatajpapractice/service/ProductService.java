package org.example.springdatajpapractice.service;

import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.model.Product;
import org.example.springdatajpapractice.repository.CategoryRepository;
import org.example.springdatajpapractice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public Product create(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));

        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));
    }
}

package org.example.springdatajpapractice.repository;

import org.example.springdatajpapractice.model.Category;
import org.example.springdatajpapractice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByPriceBetween(Double min, Double max);

}

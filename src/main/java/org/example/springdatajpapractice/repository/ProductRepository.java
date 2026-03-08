package org.example.springdatajpapractice.repository;

import org.example.springdatajpapractice.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByPriceBetween(Double min, Double max, Pageable pageable);
    @Query("""
select p from Product p
join fetch p.category
""")
    List<Product> findAllWithCategory();
}

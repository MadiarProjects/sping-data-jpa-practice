package org.example.springdatajpapractice.repository;

import org.example.springdatajpapractice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // запросные
    Category findByName(String name); // select c from Category c where c.name = :name

    Category findByNameOrId(String name, int id); // select c from Category c where c.name = :name or c.id = :id
    Category findByNameAndId(String name, int id); // select c from Category c where c.name = :name and c.id = :id
    Category findByNameAndName(String name1, String name2); // select c from Category c where c.name = :name and c.id = :id

    List<Category> findByNameContaining(String str); // Проц    Процессоры

    List<Category> findByNameContainingIgnoreCase(String str); // проц    Процессоры

    // GET /products?min=150000&max=250000

    // querydsl
    // mybatis
    // spring jdbc
    // jpa
}
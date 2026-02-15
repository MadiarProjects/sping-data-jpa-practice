package org.example.springdatajpapractice.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.springdatajpapractice.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Integer> {
    Option findById(Long id);
}

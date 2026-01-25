package org.example.springdatajpapractice.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.springdatajpapractice.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}

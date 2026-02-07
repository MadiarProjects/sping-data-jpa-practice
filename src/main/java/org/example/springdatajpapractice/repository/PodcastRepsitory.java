package org.example.springdatajpapractice.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.springdatajpapractice.model.Host;
import org.example.springdatajpapractice.model.Language;
import org.example.springdatajpapractice.model.Podcast;
import org.example.springdatajpapractice.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PodcastRepsitory extends JpaRepository<Podcast,Integer> {
    @Query("""
                select p from Podcast p
                where (:title is null or lower(p.title) like lower(concat('%',cast(:title as text),'%')))
                and (:category is null or p.theme in (:category))
                and (:language is null or p.language in (:language))
                and (:minDuration is null or p.duration >=:minDuration)
                and(:maxDuration is null or p.duration <=:maxDuration)
                and (:year is null or p.releaseYear in (:year))
                and (:host is null or p.host.id in (:host))
            """)
    List<Podcast> findByMultiCondition(String title, List<Theme>category, List<Language>language, Integer minDuration, Integer maxDuration, List<Integer> year, List<Integer> host);

}

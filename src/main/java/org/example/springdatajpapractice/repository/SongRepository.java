package org.example.springdatajpapractice.repository;

import org.example.springdatajpapractice.model.Artist;
import org.example.springdatajpapractice.model.Country;
import org.example.springdatajpapractice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Integer> {
    @Query("""
            select s from Song s
            where (:title is null or lower(s.title) like lower( concat('%', cast(:title as text ), '%')))
            and (:genre is null or s.genre in (:genre))
            and (:year is null or s.releaseYear in (:year))
            and (:minplaycount is null or s.playcount >=:minplaycount)
            and (:artist is null or s.artist.id in (:artist))
            and (:artistcountry is null or s.artist.country in (:artistcountry))
            """)
    List<Song> findByMultipleCondition(String title, List<Song.Genre> genre, List<Integer> year, Integer minplaycount, List<Integer> artist, List<Country> artistcountry);
}

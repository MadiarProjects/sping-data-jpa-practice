package org.example.springdatajpapractice.repository;

import org.example.springdatajpapractice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Integer> {
}

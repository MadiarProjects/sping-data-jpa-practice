package org.example.springdatajpapractice.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private int duration;
    private Genre genre;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "release_year")
    private int releaseYear;
    private int playcount;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    public enum Genre{
        ROCK,POP,INDIE
    }
}

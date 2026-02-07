package org.example.springdatajpapractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "podcasts")
public class Podcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private  String description;
    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private Theme theme;
    @Enumerated(value = EnumType.STRING)
    private Language language;
    private Integer duration;
    @Column(name="release_year")
    private Integer releaseYear;
    @JoinColumn(name = "host_id")
    @ManyToOne
    private Host host;
}


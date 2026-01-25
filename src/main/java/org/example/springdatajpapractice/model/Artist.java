package org.example.springdatajpapractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity

@Setter
@Getter
@ToString
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private Country country;
    @OneToMany(mappedBy = "artist")
    private List<Song> songs;
    public enum Country{
        USA,UK,SOUTH_KOREA,KAZAKHSTAN,RUSSIA,SPAIN,FRANCE
    }
}

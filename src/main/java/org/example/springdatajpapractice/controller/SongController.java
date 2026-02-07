package org.example.springdatajpapractice.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.model.Artist;
import org.example.springdatajpapractice.model.Country;
import org.example.springdatajpapractice.model.Song;
import org.example.springdatajpapractice.repository.SongRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {
    private final SongRepository songRepository;
    @GetMapping
    public List<Song> getAll(
            @RequestParam(required = false)String title,
            @RequestParam(required = false)List<Song.Genre>genre,
            @RequestParam(required = false)List<Integer>year,
            @RequestParam(required = false)Integer minplaycount,
            @RequestParam(required = false)List<Integer>artist,
            @RequestParam(required = false)List<Country>artistcountry
    ){
        return songRepository.findByMultipleCondition(title,genre,year,minplaycount,artist,artistcountry);
    }
}

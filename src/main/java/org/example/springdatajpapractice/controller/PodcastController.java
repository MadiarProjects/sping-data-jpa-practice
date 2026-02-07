package org.example.springdatajpapractice.controller;


import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.model.Language;
import org.example.springdatajpapractice.model.Podcast;
import org.example.springdatajpapractice.model.Theme;
import org.example.springdatajpapractice.repository.PodcastRepsitory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/podcasts")
public class PodcastController {
    private final PodcastRepsitory podcastRepsitory;
    @GetMapping
    public List<Podcast> getAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false)List<Theme> category,
            @RequestParam(required = false)List<Language>language,
            @RequestParam(required = false)Integer minDuration,
            @RequestParam(required = false)Integer maxDuration,
            @RequestParam(required = false)List<Integer>year,
            @RequestParam(required = false)List<Integer> host
            ){
        return podcastRepsitory.findByMultiCondition(title, category, language, minDuration, maxDuration, year, host);
    }
}

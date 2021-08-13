package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAll(@RequestParam(name = "genreId", defaultValue = "0") Long genreId, Pageable pageable) {
        Page<MovieDTO> movies = service.findAllPaged(genreId, pageable);
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO movie = service.findById(id);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewsByMovie(@PathVariable Long id) {
        List<ReviewDTO> reviews = reviewService.findByMovie(id);
        return ResponseEntity.ok().body(reviews);
    }
}

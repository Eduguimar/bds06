package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private GenreRepository genreRepository;

    public Page<MovieDTO> findAllPaged(Long genreId, Pageable pageable) {
        Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
        Page<Movie> pages = repository.find(genre, pageable);
        return pages.map(movie -> new MovieDTO(movie));
    }

}

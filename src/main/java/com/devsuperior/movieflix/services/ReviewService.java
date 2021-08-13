package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    public List<ReviewDTO> findByMovie(Long movieId) {
        User user = authService.authenticated();
        Movie movie = movieRepository.getOne(movieId);
        List<Review> reviews = repository.findByMovie(movie);

        return reviews.stream().map(review -> new ReviewDTO(review, user)).collect(Collectors.toList());
    }



    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        User user = authService.authenticated();

        Review entity = new Review();
        entity.setText(dto.getText());
        entity.setMovie(movieRepository.findById(dto.getMovieId()).get());
        entity.setUser(user);

        entity = repository.save(entity);

        return new ReviewDTO(entity, user);
    }
}

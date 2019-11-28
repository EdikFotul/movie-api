package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public void addOrUpdate(Movie movie) {
        movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public Optional<Movie> get(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getAll() {
        Iterable<Movie> movieIterable = movieRepository.findAll();
        return transform(movieIterable);
    }

    private List<Movie> transform(Iterable<Movie> movieIterable) {
        List<Movie> movies = new ArrayList<>();
        movieIterable.forEach(movies::add);
        return movies;
    }
}

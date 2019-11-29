package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public MovieDto get(Long id) {
        Optional<Movie> actor = movieRepository.findById(id);
        return actor.map(this::transformMovieToMovieDto).orElse(null);
    }

    public List<MovieDto> getAll() {
        Iterable<Movie> movieIterable = movieRepository.findAll();
        return transformIterableToList(movieIterable);
    }

    private List<MovieDto> transformIterableToList(Iterable<Movie> movieIterable) {
        List<Movie> movies = new ArrayList<>();
        movieIterable.forEach(movies::add);
        return movies.stream().map(this::transformMovieToMovieDto).collect(Collectors.toList());
    }

    private MovieDto transformMovieToMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setMovieName(movie.getMovieName());
        movieDto.setReleaseDate(movie.getReleaseDate());
        movieDto.setActors(movie.getActors().stream().map(Actor::getActorName).collect(Collectors.toList()));
        movieDto.setDirector(movie.getDirector().getDirectorName());
        return movieDto;
    }
}

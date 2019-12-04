package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.mapper.MovieMapper;
import com.efotul.movie.movieapi.repository.MovieRepository;
import com.efotul.movie.movieapi.repository.MovieRepositoryImproved;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private final MovieRepositoryImproved movieRepositoryImproved;

    private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    public void addOrUpdate(MovieDto movie) {
        movieRepository.save(movieMapper.movieDtoToMovie(movie));
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public MovieDto get(Long id) {
        Optional<Movie> actor = movieRepository.findById(id);
        return actor.map(movieMapper::movieToMovieDto).orElse(null);
    }

    public List<MovieDto> getAll() {
        Iterable<Movie> movieIterable = movieRepository.findAll();
        return transformIterableToList(movieIterable);
    }

    private List<MovieDto> transformIterableToList(Iterable<Movie> movieIterable) {
        List<Movie> movies = new ArrayList<>();
        movieIterable.forEach(movies::add);
        return movies.stream().map(movieMapper::movieToMovieDto).collect(Collectors.toList());
    }

    public List<MovieDto> getByCondition(Long years, Double experience) {
        Timestamp untilDate = null;
        if (years != null) {
            untilDate = Timestamp.valueOf(LocalDateTime.now().minusYears(years));
        }
        Iterable<Movie> movieIterable = movieRepositoryImproved.findByReleaseDateAndAvgActorsExperience(untilDate, experience);
        return transformIterableToList(movieIterable);
    }
}

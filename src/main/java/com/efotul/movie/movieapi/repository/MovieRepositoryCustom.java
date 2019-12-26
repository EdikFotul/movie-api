package com.efotul.movie.movieapi.repository;

import com.efotul.movie.movieapi.entity.Movie;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface MovieRepositoryCustom {

    Iterable<Movie> findByReleaseDateAndAvgActorsExperience(LocalDateTime releaseDate, Double avgExperience);
}

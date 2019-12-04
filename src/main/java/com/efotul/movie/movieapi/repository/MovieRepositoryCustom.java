package com.efotul.movie.movieapi.repository;

import com.efotul.movie.movieapi.entity.Movie;

import java.sql.Timestamp;

public interface MovieRepositoryCustom {

    Iterable<Movie> findByReleaseDateAndAvgActorsExperience(Timestamp releaseDate, Double avgExperience);
}

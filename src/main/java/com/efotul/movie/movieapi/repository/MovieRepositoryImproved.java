package com.efotul.movie.movieapi.repository;

import com.efotul.movie.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryImproved extends JpaRepository<Movie, Long>, MovieRepositoryCustom {
}

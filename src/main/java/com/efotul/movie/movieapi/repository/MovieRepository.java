package com.efotul.movie.movieapi.repository;

import com.efotul.movie.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, JpaRepository<Movie, Long>, MovieRepositoryCustom{
}

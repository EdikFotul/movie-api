package com.efotul.movie.movieapi.repository;

import com.efotul.movie.movieapi.entity.Director;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Long> {
}

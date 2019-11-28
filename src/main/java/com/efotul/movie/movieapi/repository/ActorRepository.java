package com.efotul.movie.movieapi.repository;

import com.efotul.movie.movieapi.entity.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {

    Optional<Actor> findActorByActorName(String actorName);
}

package com.efotul.movie.movieapi.repository;

import com.efotul.movie.movieapi.entity.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorCrudRepository extends CrudRepository<Actor, Long> {

    Actor save(Actor actor);

}

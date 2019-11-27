package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.repository.ActorCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ActorService {

    @Autowired
    private ActorCrudRepository actorCrudRepository;

    @Transactional
    public void addActor(Actor actor) {
        Actor savedActor = actorCrudRepository.save(actor);
    }
}

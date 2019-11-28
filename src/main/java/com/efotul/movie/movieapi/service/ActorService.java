package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ActorService {

    private final ActorRepository actorRepository;

    public void addActor(Actor actor) {
        actorRepository.save(actor);
    }

    public void delete(Long id) {
        actorRepository.deleteById(id);
    }

    public Optional<Actor> get(Long id) {
        return actorRepository.findById(id);
    }

    public List<Actor> getAll() {
        Iterable<Actor> actorIterable = actorRepository.findAll();
        return transform(actorIterable);
    }

    private List<Actor> transform(Iterable<Actor> actorIterable) {
        List<Actor> actors = new ArrayList<>();
        actorIterable.forEach(actors::add);
        return actors;
    }
}

package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ActorDto get(Long id) {
        Optional<Actor> actor = actorRepository.findById(id);
        return actor.map(this::transformActorToActorDto).orElse(null);
    }

    public List<ActorDto> getAll() {
        Iterable<Actor> actorIterable = actorRepository.findAll();
        return transformIterableToList(actorIterable);
    }

    private List<ActorDto> transformIterableToList(Iterable<Actor> actorIterable) {
        List<Actor> actors = new ArrayList<>();
        actorIterable.forEach(actors::add);
        return actors.stream().map(this::transformActorToActorDto).collect(Collectors.toList());
    }

    private ActorDto transformActorToActorDto(Actor actor) {
        ActorDto actorDto = new ActorDto();
        actorDto.setId(actor.getId());
        actorDto.setActorName(actor.getActorName());
        actorDto.setExperience(actor.getExperience());
        actorDto.setMovies(actor.getMovies().stream().map(Movie::getMovieName).collect(Collectors.toList()));
        return actorDto;
    }
}
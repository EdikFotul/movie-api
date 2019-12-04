package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.mapper.ActorActorDtoMapper;
import com.efotul.movie.movieapi.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
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

    private ActorActorDtoMapper actorMapper = Mappers.getMapper(ActorActorDtoMapper.class);

    public void addActor(ActorDto actorDto) {
        Actor actorEntity = actorMapper.actorDtoToActor(actorDto);
        actorRepository.save(actorEntity);
    }

    public void delete(Long id) {
        actorRepository.deleteById(id);
    }

    public ActorDto get(Long id) {
        Optional<Actor> actor = actorRepository.findById(id);
        return actor.map(actorMapper::actorToActorDto).orElse(null);
    }

    public List<ActorDto> getAll() {
        Iterable<Actor> actorIterable = actorRepository.findAll();
        return transformIterableToList(actorIterable);
    }

    private List<ActorDto> transformIterableToList(Iterable<Actor> actorIterable) {
        List<Actor> actors = new ArrayList<>();
        actorIterable.forEach(actors::add);
        return actors.stream().map(actorMapper::actorToActorDto).collect(Collectors.toList());
    }
}
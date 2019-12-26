package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.exeptions.NoSuchActorException;
import com.efotul.movie.movieapi.mapper.ActorMapper;
import com.efotul.movie.movieapi.model.ActorModel;
import com.efotul.movie.movieapi.repository.ActorRepository;
import com.efotul.movie.movieapi.repository.MovieRepository;
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

    private final MovieRepository movieRepository;

    private ActorMapper actorMapper = Mappers.getMapper(ActorMapper.class);

    public void addActor(ActorModel actorModel) throws NoSuchActorException {
        if (actorModel.getId() != null) {
            if (!actorRepository.findById(actorModel.getId()).isPresent()) {
                throw new NoSuchActorException("Actor with id = " + actorModel.getId() + " does not exist!");
            }
        }
        Actor actorEntity = actorMapper.actorModelToActor(actorModel);
        if (actorModel.getMoviesId() != null){
            List<Movie> movies = new ArrayList<>();
            for (Long id : actorModel.getMoviesId()) {
                Movie movie = movieRepository.getOne(id);
                movie.getActors().add(actorEntity);
                movies.add(movie);
            }
            actorEntity.setMovies(movies);
        }
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

package com.efotul.movie.movieapi.mapper;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.model.ActorModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface ActorMapper {

    Actor actorDtoToActor(ActorDto actorDto);

    ActorDto actorToActorDto(Actor actor);

    @IterableMapping(qualifiedByName = "mapWithoutActors")
    List<MovieDto> movieListToMovieDtoList(List<Movie> actors);

    @Named(value = "mapWithoutActors")
    @Mapping(target = "actors", ignore = true)
    MovieDto mapWithoutActors(Movie movie);


    @Mapping(target = "movies", ignore = true)
    Actor actorModelToActor(ActorModel actorModel);
}

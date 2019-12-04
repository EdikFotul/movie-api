package com.efotul.movie.movieapi.mapper;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.dto.DirectorDto;
import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.entity.Director;
import com.efotul.movie.movieapi.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MovieMapper {

    Movie movieDtoToMovie(MovieDto movieDto);

    MovieDto movieToMovieDto(Movie movie);

    @Mapping(target = "movies", ignore = true)
    DirectorDto directorToDirectorDto(Director director);

    @Mapping(target = "movies", ignore = true)
    ActorDto actorToActorDto(Actor actor);
}

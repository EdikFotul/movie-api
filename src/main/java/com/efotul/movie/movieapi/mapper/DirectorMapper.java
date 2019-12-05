package com.efotul.movie.movieapi.mapper;

import com.efotul.movie.movieapi.dto.DirectorDto;
import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.entity.Director;
import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.model.DirectorModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface DirectorMapper {

    DirectorDto directorToDirectorDto(Director director);

    Director directorDtoToDirector(DirectorDto directorDto);

    @IterableMapping(qualifiedByName = "mapWithoutActors")
    List<MovieDto> movieListToMovieDtoList(List<Movie> actors);

    @Named(value = "mapWithoutActors")
    @Mapping(target = "actors", ignore = true)
    @Mapping(target = "director", ignore = true)
    MovieDto mapWithoutActors(Movie movie);

    @Mapping(target = "movies", ignore = true)
    Director directorModelToDirector(DirectorModel directorModel);
}

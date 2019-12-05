package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.entity.Director;
import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.mapper.MovieMapper;
import com.efotul.movie.movieapi.model.MovieModel;
import com.efotul.movie.movieapi.repository.ActorRepository;
import com.efotul.movie.movieapi.repository.DirectorRepository;
import com.efotul.movie.movieapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private final ActorRepository actorRepository;

    private final DirectorRepository directorRepository;

    private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    public void saveOrUpdate(MovieModel movieModel) {
        Movie movie = movieMapper.movieModelToMovie(movieModel);
        if (movieModel.getActorsId() != null){
            List<Actor> actors = new ArrayList<>();
            for (Long id: movieModel.getActorsId()) {
                Actor actor = actorRepository.getOne(id);
                actor.getMovies().add(movie);
                actors.add(actor);
            }
            movie.setActors(actors);
        }

        if (movieModel.getDirectorId() != null){
            Director director = directorRepository.getOne(movieModel.getDirectorId());
            movie.setDirector(director);
        }
        movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public MovieDto get(Long id) {
        Optional<Movie> actor = movieRepository.findById(id);
        return actor.map(movieMapper::movieToMovieDto).orElse(null);
    }

    public List<MovieDto> getAll() {
        Iterable<Movie> movieIterable = movieRepository.findAll();
        return transformIterableToList(movieIterable);
    }

    private List<MovieDto> transformIterableToList(Iterable<Movie> movieIterable) {
        List<Movie> movies = new ArrayList<>();
        movieIterable.forEach(movies::add);
        return movies.stream().map(movieMapper::movieToMovieDto).collect(Collectors.toList());
    }

    public List<MovieDto> getByCondition(Long years, Double experience) {
        Timestamp untilDate = null;
        if (years != null) {
            untilDate = Timestamp.valueOf(LocalDateTime.now().minusYears(years));
        }
        Iterable<Movie> movieIterable = movieRepository.findByReleaseDateAndAvgActorsExperience(untilDate, experience);
        return transformIterableToList(movieIterable);
    }
}

package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.DirectorDto;
import com.efotul.movie.movieapi.entity.Director;
import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.mapper.DirectorMapper;
import com.efotul.movie.movieapi.model.DirectorModel;
import com.efotul.movie.movieapi.repository.DirectorRepository;
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
@Transactional
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    private final DirectorMapper directorMapper = Mappers.getMapper(DirectorMapper.class);

    private final MovieRepository movieRepository;

    public void addOrUpdate(DirectorModel directorModel) {
        Director director = directorMapper.directorModelToDirector(directorModel);
        if (directorModel.getMoviesId() != null){
            List<Movie> movies = new ArrayList<>();
            for (Long id : directorModel.getMoviesId()) {
                Movie movie = movieRepository.getOne(id);
                movie.setDirector(director);
                movies.add(movie);
            }
            director.setMovies(movies);
        }
        directorRepository.save(director);
    }

    public void delete(Long id) {
        directorRepository.deleteById(id);
    }

    public DirectorDto get(Long id) {
        Optional<Director> director = directorRepository.findById(id);
        return director.map(directorMapper::directorToDirectorDto).orElse(null);
    }

    public List<DirectorDto> getAll() {
        Iterable<Director> directorIterable = directorRepository.findAll();
        return transformIterableToList(directorIterable);
    }

    private List<DirectorDto> transformIterableToList(Iterable<Director> directorIterable) {
        List<Director> directors = new ArrayList<>();
        directorIterable.forEach(directors::add);
        return directors.stream().map(directorMapper::directorToDirectorDto).collect(Collectors.toList());
    }

}

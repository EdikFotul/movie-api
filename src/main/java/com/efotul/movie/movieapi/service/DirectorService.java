package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.DirectorDto;
import com.efotul.movie.movieapi.entity.Director;
import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
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

    public void addOrUpdate(Director director) {
        directorRepository.save(director);
    }

    public void delete(Long id) {
        directorRepository.deleteById(id);
    }

    public DirectorDto get(Long id) {
        Optional<Director> director = directorRepository.findById(id);
        return director.map(this::transformDirectorToDirectorDto).orElse(null);
    }

    public List<DirectorDto> getAll() {
        Iterable<Director> directorIterable = directorRepository.findAll();
        return transformIterableToList(directorIterable);
    }

    private List<DirectorDto> transformIterableToList(Iterable<Director> directorIterable) {
        List<Director> directors = new ArrayList<>();
        directorIterable.forEach(directors::add);
        return directors.stream().map(this::transformDirectorToDirectorDto).collect(Collectors.toList());
    }

    private DirectorDto transformDirectorToDirectorDto(Director director) {
        DirectorDto directorDto = new DirectorDto();
        directorDto.setId(director.getId());
        directorDto.setDirectorName(director.getDirectorName());
        directorDto.setMovies(director.getMovies().stream().map(Movie::getMovieName).collect(Collectors.toList()));
        return directorDto;
    }

}

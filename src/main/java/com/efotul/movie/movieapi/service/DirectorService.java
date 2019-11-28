package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.entity.Director;
import com.efotul.movie.movieapi.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Director> get(Long id) {
        return directorRepository.findById(id);
    }

    public List<Director> getAll() {
        Iterable<Director> directorIterable = directorRepository.findAll();
        return transform(directorIterable);
    }

    private List<Director> transform(Iterable<Director> directorIterable) {
        List<Director> directors = new ArrayList<>();
        directorIterable.forEach(directors::add);
        return directors;
    }

}

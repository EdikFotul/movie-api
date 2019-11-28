package com.efotul.movie.movieapi.rest;

import com.efotul.movie.movieapi.entity.Movie;
import com.efotul.movie.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieRest {

    private final MovieService movieService;

    @PostMapping
    public void saveOrUpdate(@RequestBody Movie movie) {
        movieService.addOrUpdate(movie);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);

    }

    @GetMapping("{id}")
    public Optional<Movie> get(@PathVariable Long id) {
        return movieService.get(id);
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieService.getAll();
    }
}

package com.efotul.movie.movieapi.rest;

import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.model.MovieModel;
import com.efotul.movie.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieRest {

    private final MovieService movieService;

    @PostMapping
    public void saveOrUpdate(@RequestBody MovieModel movie) {
        movieService.saveOrUpdate(movie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

    @GetMapping("/{id}")
    public MovieDto get(@PathVariable Long id) {
        return movieService.get(id);
    }

    @GetMapping
    public List<MovieDto> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/filter")
    public List<MovieDto> getByCondition(@RequestParam Long years, @RequestParam Double experience) {
        return movieService.getByCondition(years, experience);
    }
}

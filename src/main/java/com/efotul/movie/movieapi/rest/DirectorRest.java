package com.efotul.movie.movieapi.rest;


import com.efotul.movie.movieapi.entity.Director;
import com.efotul.movie.movieapi.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/directors")
public class DirectorRest {

    private final DirectorService directorService;

    @PostMapping
    public void saveOrUpdate(@RequestBody Director director) {
        directorService.addOrUpdate(director);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        directorService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Director> get(@PathVariable Long id) {
        return directorService.get(id);
    }

    @GetMapping
    public List<Director> getAll() {
        return directorService.getAll();
    }
}

package com.efotul.movie.movieapi.rest;


import com.efotul.movie.movieapi.dto.DirectorDto;
import com.efotul.movie.movieapi.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/directors")
public class DirectorRest {

    private final DirectorService directorService;

    @PostMapping
    public void saveOrUpdate(@RequestBody DirectorDto director) {
        directorService.addOrUpdate(director);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        directorService.delete(id);
    }

    @GetMapping("/{id}")
    public DirectorDto get(@PathVariable Long id) {
        return directorService.get(id);
    }

    @GetMapping
    public List<DirectorDto> getAll() {
        return directorService.getAll();
    }
}

package com.efotul.movie.movieapi.rest;


import com.efotul.movie.movieapi.dto.DirectorDto;
import com.efotul.movie.movieapi.model.DirectorModel;
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
    public void saveOrUpdate(@RequestBody DirectorModel directorModel) {
        directorService.addOrUpdate(directorModel);
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

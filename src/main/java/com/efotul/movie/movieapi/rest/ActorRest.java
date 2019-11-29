package com.efotul.movie.movieapi.rest;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actors")
public class ActorRest {

    private final ActorService actorService;

    @PostMapping
    public void saveOrUpdate(@RequestBody Actor actor) {
        actorService.addActor(actor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        actorService.delete(id);
    }

    @GetMapping("/{id}")
    public ActorDto get(@PathVariable Long id) {
        return actorService.get(id);
    }

    @GetMapping
    public List<ActorDto> getAll() {
        return actorService.getAll();
    }
}

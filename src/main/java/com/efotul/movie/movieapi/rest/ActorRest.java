package com.efotul.movie.movieapi.rest;

import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actor")
public class ActorRest {

    private final ActorService actorService;

    @PostMapping
    public void saveOrUpdate(@RequestBody Actor actor) {
        actorService.addActor(actor);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        actorService.delete(id);
    }

    @GetMapping("{id}")
    public Optional<Actor> get(@PathVariable Long id) {
        return actorService.get(id);
    }
}

package com.efotul.movie.movieapi.controller;

import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping(value = "/actor")
    public void addActor(@RequestBody Actor actor) {
        actorService.addActor(actor);
    }
}

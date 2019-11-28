package com.efotul.movie.movieapi.rest;

import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.repository.ActorRepository;
import com.efotul.movie.movieapi.service.ActorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ActorRestTest {

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void createActorTest() {
        Actor actor = new Actor();
        actor.setActorName(LocalDateTime.now().toString());
        actor.setExperience(4D);

        actorService.addActor(actor);

        Optional<Actor> actorFromRepository = actorRepository.findActorByActorName(actor.getActorName());
        assertTrue(actorFromRepository.isPresent());
        assertThat(actor.getActorName()).isEqualTo(actorFromRepository.get().getActorName());
    }

    @Test
    public void getActorTest() {
        Actor actor = new Actor();
        actor.setActorName(LocalDateTime.now().toString());
        actor.setExperience(4D);

        actorService.addActor(actor);

        Optional<Actor> actorFromRepository = actorRepository.findActorByActorName(actor.getActorName());
        assertTrue(actorFromRepository.isPresent());

        Long id = actorFromRepository.get().getId();

        assertTrue(actorService.get(id).isPresent());
        assertThat(actor.getActorName()).isEqualTo(actorService.get(id).get().getActorName());
    }

    @Test
    public void deleteActorTest() {
        Actor actor = new Actor();
        actor.setActorName(LocalDateTime.now().toString());
        actor.setExperience(4D);

        actorService.addActor(actor);

        Optional<Actor> actorFromRepository = actorRepository.findActorByActorName(actor.getActorName());
        assertTrue(actorFromRepository.isPresent());

        Long id = actorFromRepository.get().getId();
        actorService.delete(id);

        assertFalse(actorService.get(id).isPresent());
    }
}
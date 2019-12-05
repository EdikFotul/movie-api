package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.IntegrationTestImpl;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.model.ActorModel;
import com.efotul.movie.movieapi.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Transactional
@Rollback
class ActorServiceTest extends IntegrationTestImpl {

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void createActorTest() {
        ActorModel actor = new ActorModel();
        actor.setActorName(LocalDateTime.now().toString());
        actor.setExperience(4D);

        actorService.addActor(actor);

        Optional<Actor> actorFromRepository = actorRepository.findActorByActorName(actor.getActorName());
        assertTrue(actorFromRepository.isPresent());
        assertThat(actor.getActorName()).isEqualTo(actorFromRepository.get().getActorName());
    }

    @Test
    void getActorTest() {
        ActorModel actor = new ActorModel();
        actor.setActorName(LocalDateTime.now().toString());
        actor.setExperience(4D);

        actorService.addActor(actor);

        Optional<Actor> actorFromRepository = actorRepository.findActorByActorName(actor.getActorName());
        assertTrue(actorFromRepository.isPresent());

        Long id = actorFromRepository.get().getId();

        assertThat(actor.getActorName()).isEqualTo(actorService.get(id).getActorName());
    }

    @Test
    void deleteActorTest() {
        ActorModel actor = new ActorModel();
        actor.setActorName(LocalDateTime.now().toString());
        actor.setExperience(4D);

        actorService.addActor(actor);

        Optional<Actor> actorFromRepository = actorRepository.findActorByActorName(actor.getActorName());
        assertTrue(actorFromRepository.isPresent());

        Long id = actorFromRepository.get().getId();
        actorService.delete(id);

        assertNull(actorService.get(id));
    }
}
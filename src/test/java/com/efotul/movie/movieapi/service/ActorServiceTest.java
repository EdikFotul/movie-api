package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ActorServiceTest {

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void createActorTest() {
        ActorDto actor = new ActorDto();
        actor.setActorName(LocalDateTime.now().toString());
        actor.setExperience(4D);

        actorService.addActor(actor);

        Optional<Actor> actorFromRepository = actorRepository.findActorByActorName(actor.getActorName());
        assertTrue(actorFromRepository.isPresent());
        assertThat(actor.getActorName()).isEqualTo(actorFromRepository.get().getActorName());
    }

    @Test
    void getActorTest() {
        ActorDto actor = new ActorDto();
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
        ActorDto actor = new ActorDto();
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
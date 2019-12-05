package com.efotul.movie.movieapi.service;

import com.efotul.movie.movieapi.IntegrationTestImpl;
import com.efotul.movie.movieapi.dto.ActorDto;
import com.efotul.movie.movieapi.dto.MovieDto;
import com.efotul.movie.movieapi.model.MovieModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MovieServiceTest extends IntegrationTestImpl {

    @Autowired
    private MovieService movieService;

    @Test
    @Transactional
    @Rollback
    void getMoviesByCondition() {
        MovieModel movieModel = new MovieModel();
        movieModel.setReleaseDate(Timestamp.valueOf(LocalDateTime.now().minusYears(10).plusHours(2)));
        movieModel.setMovieName("Test" + LocalDateTime.now().toString());
        List<Long> actorsId = new ArrayList<>();
        actorsId.add(25L);
        actorsId.add(24L);
        movieModel.setActorsId(actorsId);
        movieModel.setDirectorId(6L);

        movieService.saveOrUpdate(movieModel);

        List<MovieDto> movieDtoList = movieService.getByCondition(10L, 1000D);
        assertThat(movieDtoList.get(0).getReleaseDate()).isEqualTo(movieModel.getReleaseDate());
        assertThat(movieDtoList.get(0).getActors().stream().mapToDouble(ActorDto::getExperience).average().orElse(Double.NaN)).isEqualTo(1000D);
    }
}

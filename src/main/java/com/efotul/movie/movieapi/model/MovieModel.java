package com.efotul.movie.movieapi.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MovieModel {

    private Long id;
    private String movieName;
    private LocalDateTime releaseDate;
    private List<Long> actorsId;
    private Long directorId;
}

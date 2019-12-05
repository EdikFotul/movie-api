package com.efotul.movie.movieapi.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MovieModel {

    private Long id;
    private String movieName;
    private Timestamp releaseDate;
    private List<Long> actorsId;
    private Long directorId;
}

package com.efotul.movie.movieapi.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MovieDto {

    private Long id;
    private String movieName;
    private Timestamp releaseDate;
    private List<ActorDto> actors;
    private DirectorDto director;
}

package com.efotul.movie.movieapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDto {

    private Long id;
    private String movieName;
    private Timestamp releaseDate;
    private List<ActorDto> actors;
    private DirectorDto director;
}

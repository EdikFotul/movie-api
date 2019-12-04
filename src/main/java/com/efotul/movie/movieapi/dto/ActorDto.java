package com.efotul.movie.movieapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorDto {

    private Long id;
    private String actorName;
    private Double experience;
    private List<MovieDto> movies;
}

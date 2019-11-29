package com.efotul.movie.movieapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ActorDto {

    private Long id;
    private String actorName;
    private Double experience;
    private List<String> movies;
}

package com.efotul.movie.movieapi.model;

import lombok.Data;

import java.util.List;

@Data
public class ActorModel {

    private Long id;
    private String actorName;
    private Double experience;
    private List<Long> moviesId;
}

package com.efotul.movie.movieapi.model;

import lombok.Data;

import java.util.List;

@Data
public class DirectorModel {

    private Long id;
    private String directorName;
    private List<Long> moviesId;
}
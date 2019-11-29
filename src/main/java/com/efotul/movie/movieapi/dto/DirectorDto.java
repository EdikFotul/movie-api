package com.efotul.movie.movieapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class DirectorDto {

    private Long id;
    private String directorName;
    private List<String> movies;
}

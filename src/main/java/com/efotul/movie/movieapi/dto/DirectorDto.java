package com.efotul.movie.movieapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectorDto {

    private Long id;
    private String directorName;
    private List<MovieDto> movies;
}

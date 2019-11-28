package com.efotul.movie.movieapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String directorName;

    @OneToMany(mappedBy = "director")
    private List<Movie> movies;


}

package com.efotul.movie.movieapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "actor")
@Data
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actorName;

    private Double experience;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}

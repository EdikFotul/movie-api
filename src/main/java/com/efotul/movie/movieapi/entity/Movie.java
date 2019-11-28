package com.efotul.movie.movieapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieName;

    private Timestamp releaseDate;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
}




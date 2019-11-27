package com.efotul.movie.movieapi.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MOVIES")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(name = "movie_name")
    @NonNull
    private String movieName;

    @Column(name = "release_date")
    @NonNull
    private Timestamp releaseDate;

    @NonNull
    @OneToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private Director director;
}




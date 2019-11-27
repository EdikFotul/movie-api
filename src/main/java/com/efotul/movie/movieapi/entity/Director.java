package com.efotul.movie.movieapi.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "DIRECTORS")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    private Long directorId;

    @Column(name = "director_name")
    @NonNull
    private String directorName;

    @OneToMany(mappedBy = "director")
    private List<Movie> movies;


}

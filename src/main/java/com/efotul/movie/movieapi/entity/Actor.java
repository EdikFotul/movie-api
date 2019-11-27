package com.efotul.movie.movieapi.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "ACTORS")
@Data
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;

    @Column(name = "actor_name")
    @NonNull
    private String actorName;

    @Column(name = "experience")
    @NonNull
    private Double experience;

    @OneToOne(mappedBy = "actor")
    private Movie movie;
}

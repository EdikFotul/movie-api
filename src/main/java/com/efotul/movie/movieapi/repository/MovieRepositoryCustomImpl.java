package com.efotul.movie.movieapi.repository;

import com.efotul.movie.movieapi.entity.Actor;
import com.efotul.movie.movieapi.entity.Movie;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Timestamp;

@RequiredArgsConstructor
public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {

    private final EntityManager em;

    @Override
    public Iterable<Movie> findByReleaseDateAndAvgActorsExperience(Timestamp releaseDate, Double avgExperience) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);

        Root<Movie> movie = cq.from(Movie.class);
        Join<Actor, Movie> actors = movie.join("actors");

        Subquery<Long> subQuery = cq.subquery(Long.class);
        Root<Actor> actor = subQuery.from(Actor.class);
        Join<Actor, Movie> movies = actor.join("movies");

        Predicate releaseDatePredicate = cb.greaterThanOrEqualTo(movie.get("releaseDate"), releaseDate);
        Predicate avgExperiencePredicate = cb.greaterThanOrEqualTo(cb.avg(actor.get("experience")), avgExperience);

        subQuery.select(movies.get("id")).groupBy(movies.get("id")).having(avgExperiencePredicate);
        cq.select(movie).where(releaseDatePredicate, movie.get("id").in(subQuery)).groupBy(movie.get("id"));

        TypedQuery<Movie> query = em.createQuery(cq);
        return query.getResultList();
    }
}

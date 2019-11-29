create table if not exists actor
(
    id         bigint(20) auto_increment primary key,
    actor_name varchar(255) null,
    experience double       null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table if not exists director
(
    id            bigint(20) auto_increment primary key,
    director_name varchar(255) null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table if not exists movie
(
    id           bigint(20) auto_increment primary key,
    movie_name   varchar(255) null,
    release_date datetime     null,
    director_id  bigint       null,
    constraint fk_movie_to_director
        foreign key (director_id) references director (id)
            ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table if not exists movie_actor
(
    actors_id bigint(20) not null,
    movies_id bigint(20) not null,
    constraint fk_movie_actor_to_actor
        foreign key (actors_id) references actor (id)
            ON DELETE CASCADE,
    constraint fk_movie_actor_to_movie
        foreign key (movies_id) references movie (id)
            ON DELETE CASCADE

)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO director(director_name)
VALUES ('edik'),
       ('ivan'),
       ('shoni'),
       ('mark');

INSERT INTO actor(actor_name, experience)
VALUES ('Leonardo Dicaprio1', 1),
       ('Leonardo Dicaprio2', 1),
       ('Leonardo Dicaprio3', 1),
       ('Leonardo Dicaprio4', 1),
       ('Leonardo Dicaprio5', 1),
       ('Leonardo Dicaprio6', 1),
       ('Leonardo Dicaprio7', 1),
       ('Leonardo Dicaprio8', 1),
       ('Leonardo Dicaprio9', 1);

INSERT INTO movie(movie_name, release_date, director_id)
VALUES ('Gost Basters1', '2012-11-01', 1),
       ('Gost Basters2', '2013-07-24', 1),
       ('Gost Basters3', '2014-04-22', 2),
       ('Gost Basters4', '2015-01-15', 2),
       ('Gost Basters5', '2016-02-14', 2),
       ('Gost Basters6', '2013-01-20', 3),
       ('Gost Basters7', '2010-03-03', 3),
       ('Gost Basters8', '2009-06-13', 3),
       ('Gost Basters9', '2006-11-11', 3);

INSERT INTO movie_actor(actors_id, movies_id)
VALUES (1,1),
       (1,2),
       (9,3),
       (3,4),
       (5,5),
       (1,6),
       (1,7),
       (2,7),
       (3,3),
       (4,6),
       (5,3),
       (6,3),
       (2,1),
       (3,6);
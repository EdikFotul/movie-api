INSERT INTO director(id, director_name)
VALUES (5, 'Erik Crappie'),
       (6, 'Luck Basso'),
       (7, 'Evie Rockiness'),
       (8, 'Pablo Esteban');

INSERT INTO actor(id, actor_name, experience)
VALUES (10, 'Leonardo Dicaprio10', 10),
       (11, 'Leonardo Dicaprio11', 9),
       (12, 'Leonardo Dicaprio12', 11),
       (13, 'Leonardo Dicaprio13', 15),
       (14, 'Leonardo Dicaprio14', 5),
       (15, 'Leonardo Dicaprio15', 4),
       (16, 'Leonardo Dicaprio16', 4),
       (17, 'Leonardo Dicaprio17', 6),
       (18, 'Leonardo Dicaprio18', 7),
       (19, 'Leonardo Dicaprio19', 14),
       (20, 'Leonardo Dicaprio20', 13),
       (21, 'Leonardo Dicaprio21', 11),
       (22, 'Leonardo Dicaprio22', 10),
       (23, 'Leonardo Dicaprio23', 15),
       (24, 'Leonardo Dicaprio24', 1000),
       (25, 'Leonardo Dicaprio25', 1000);

INSERT INTO movie(id, movie_name, release_date, director_id)
VALUES (10, 'Gost Basters10', '2012-11-01 12:08:17.320053-03', 5),
       (11, 'Gost Basters11', '2013-07-24 12:08:17.320053-03', 5),
       (12, 'Gost Basters12', '2014-04-22 12:08:17.320053-03', 5),
       (13, 'Gost Basters13', '2015-01-15 12:08:17.320053-03', 6),
       (14, 'Gost Basters14', '2016-02-14 12:08:17.320053-03', 6),
       (15, 'Gost Basters15', '2013-01-20 12:08:17.320053-03', 7),
       (16, 'Gost Basters16', '2010-03-03 12:08:17.320053-03', 8),
       (17, 'Gost Basters17', '2009-06-13 12:08:17.320053-03', 8),
       (18, 'Gost Basters18', '2006-11-11 12:08:17.320053-03', 8);

INSERT INTO movie_actor(actors_id, movies_id)
VALUES (10,10),
       (10,11),
       (11,15),
       (11,16),
       (11,17),
       (12,10),
       (12,11),
       (12,13),
       (12,16),
       (13,13),
       (13,18),
       (13,16),
       (14,14),
       (14,10),
       (14,11),
       (14,18),
       (15,10),
       (15,11),
       (16,12),
       (16,15),
       (17,15),
       (17,16),
       (17,17),
       (18,14),
       (19,15),
       (19,11),
       (20,17),
       (20,18),
       (21,11),
       (22,14),
       (22,18),
       (23,16),
       (24,11),
       (25,10),
       (25,10);
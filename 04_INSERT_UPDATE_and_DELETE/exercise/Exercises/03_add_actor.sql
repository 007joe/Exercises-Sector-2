-- 3. Did you know Eric Stoltz was originally cast as Marty McFly in "Back to the Future"? Add Eric Stoltz to the list of actors for "Back to the Future" (1 row)
--SELECT title, person_name, actor_id, FROM movie
--JOIN movie_genre ON movie_genre.movie_id = movie.movie_id
--JOIN person ON person.person_id = movie_genre.movie_id
--JOIN movie_actor ON movie_actor.movie_id = movie_genre.movie_id
--ORDER BY title;
INSERT INTO movie_actor(actor_id, movie_id)
VALUES(7036, 105);
--WHERE title = 'Back to the Future'

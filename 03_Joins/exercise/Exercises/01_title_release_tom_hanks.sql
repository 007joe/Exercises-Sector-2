-- 1. The titles and release dates of movies that Tom Hanks has appeared in (47 rows)
SELECT title, release_date
FROM person
JOIN movie_actor ON movie_actor.actor_id = person.person_id
JOIN movie ON movie.movie_id = movie_actor.movie_id
WHERE person_name = 'Tom Hanks'

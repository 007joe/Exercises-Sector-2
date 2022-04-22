-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later (6 rows)
SELECT DISTINCT(genre_name)
FROM genre
JOIN movie_genre ON movie_genre.genre_id = genre.genre_id
JOIN movie ON movie.movie_id = movie_genre.movie_id
JOIN movie_actor ON movie_actor.movie_id = movie_genre.movie_id
JOIN person ON person.person_id = movie_actor.actor_id
WHERE person_name = 'Robert De Niro' AND release_date BETWEEN '2010-01-01' AND '2022-02-17'

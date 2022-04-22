-- 20. The titles, lengths, and release dates of the 5 longest movies in the "Action" genre. Order the movies by length (highest first), then by release date (latest first).
-- (5 rows, expected lengths around 180 - 200)
SELECT title, length_minutes, release_date
FROM movie
JOIN movie_genre ON movie_genre.movie_id = movie.movie_id
JOIN genre ON genre.genre_id = movie_genre.genre_id
Where genre_name = 'Action'
GROUP BY title, (length_minutes), release_date
ORDER BY length_minutes DESC, release_date DESC
LIMIT 5;
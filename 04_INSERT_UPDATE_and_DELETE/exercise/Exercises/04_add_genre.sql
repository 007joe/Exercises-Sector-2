-- 4. Add a "Sports" genre to the genre table. Add the movie "Coach Carter" to the newly created genre. (1 row each)
INSERT INTO genre(genre_name)
VALUES('Sports');
--SELECT * FROM GENRE
INSERT INTO movie_genre(genre_id, movie_id)
VALUES(11771, 7214);

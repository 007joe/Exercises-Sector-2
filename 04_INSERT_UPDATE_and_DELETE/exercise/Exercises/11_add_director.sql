-- 11. Hollywood is remaking the classic movie "The Blob" and doesn't have a director yet. Add yourself to the person table, and assign yourself as the director of "The Blob" (the movie is already in the movie table). (1 record each)
--select title, director_id FROM movie
--SELECT * from person
INSERT INTO person(person_name)
VALUES('Joe B')

UPDATE movie 
SET director_id = 3984916
WHERE title = 'The Blob'
				  
				  
SELECT population, state_name
FROM state
WHERE state_abbreviation != 'PA' AND state_abbreviation != 'OH';

SELECT population, state_name
FROM state
WHERE state_abbreviation <> 'PA' AND state_abbreviation <> 'OH';

SELECT state_abbreviation AS abbr, city_name AS city FROM city;

SELECT state_abbreviation, city_name FROM city;

SELECT city_name FROM city WHERE population < 100000 AND area < 100;

SELECT state_name, state_nickname 
FROM state
WHERE state_nickname IS NULL;



SELECT city_name, area, population
FROM city
WHERE state_abbreviation IN ('CT', 'ME', 'NH');

SELECT * FROM park;

SELECT park_name, (area * 1000) AS area_in_meters 
FROM park;

SELECT * FROM park 
WHERE park_name LIKE '%Grand%'


SELECT park_name, park_id, date_established
FROM park
WHERE date_established = '1916-08-09';



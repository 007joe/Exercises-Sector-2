SELECT * FROM state;
SELECT * FROM park;
SELECT * FROM city;


-- ORDERING RESULTS

-- Populations of all states from largest to smallest.
SELECT state_abbreviation, state_name, population
FROM state
ORDER BY population DESC;


-- States sorted alphabetically (A-Z) within their census region. The census regions are 
-- sorted in reverse alphabetical (Z-A) order.
SELECT state_name, census_region
FROM state
--WHERE census_region IS NOT NULL
ORDER BY census_region DESC, state_name ASC;

-- The biggest park by area
SELECT park_name, area
FROM park
ORDER BY area DESC 
LIMIT 10;


-- LIMITING RESULTS 

-- The 10 largest cities by populations
SELECT city_name, population
FROM city
ORDER BY population DESC
LIMIT 10;

-- THIS ONE USES DATES
-- The 20 oldest parks from oldest to youngest IN YEARS sorted alphabetically by name.
-- CURRENT_DATE - date_established --> RETURN # of days since date_established
SELECT CURRENT_DATE - date_established AS age, park_name 
FROM park
ORDER by age DESC, park_name ASC
LIMIT 20;

-- convert # of days into years
SELECT (CURRENT_DATE - date_established) / 365 AS age, park_name 
FROM park
ORDER by age DESC, park_name ASC
LIMIT 20;

-- using a date function
SELECT (date_part('year', CURRENT_DATE)) - (date_part('year', date_established)) AS age, park_name 
FROM park
ORDER by age DESC, park_name ASC
LIMIT 20;

-- calculate a future date 30 days from today
SELECT (CURRENT_DATE + 30) AS future


SELECT * FROM state;
SELECT * FROM park;
SELECT * FROM city;

-- CONCATENATING OUTPUTS (ONLY DO THIS IF YOU HAVE TO)

-- All city names and their state abbreviation.
SELECT (city_name || '(' || state_abbreviation || ')') AS city_state
FROM city
ORDER BY city_name;

-- The all parks by name and date established.
SELECT (park_name || ', ' || date_established) AS park_date
FROM park
ORDER BY park_date;


-- The census region and state name of all states in the West & Midwest sorted in ascending order.
SELECT (census_region || ': ' || state_name) AS region_state
FROM state
-- WHERE census_region = 'West' OR census_region = 'Midwest'
-- WHERE census_region LIKE '%west'
-- WHERE census_region ILIKE '%west'   -> Postgres specific
-- WHERE census_region IN ('West', 'Midwest')
ORDER BY region_state;

-- AGGREGATE FUNCTIONS

SELECT * FROM state;
SELECT * FROM park;
SELECT * FROM city;

-- Average population across all the states. Note the use of alias, common with aggregated values.
SELECT AVG(population) AS avg_state_population
FROM state;

-- Total population in the West and South census regions
SELECT SUM(population) AS total_population
FROM state
WHERE census_region = 'West' OR census_region = 'South';

-- The number of cities with populations greater than 1 million
SELECT count(population) as big_cities_count
FROM city
WHERE population > 1000000;


-- The number of state nicknames.  51
SELECT count(state_nickname) AS nickname_count
FROM state;

-- This counts total records in the table - 56
SELECT count(*) AS nickname_count
FROM state;

-- The area of the smallest and largest parks. (MIN and MAX)
SELECT MIN(area) AS smallest, MAX(area) AS largest
FROM park;


-- GROUP BY
SELECT * FROM state;
SELECT * FROM park;
SELECT * FROM city;
-- Count the number of cities IN EACH STATE, ordered from most cities to least.

SELECT count(city_name) AS city_count, state_abbreviation
FROM city
GROUP BY state_abbreviation
ORDER BY city_count DESC; 

-- Determine the average park area depending upon WHETHER PARKS ALLOW CAMPING OR NOT.

SELECT has_camping, avg(area) AS average_park_area
FROM park
GROUP BY has_camping;

-- Sum of the population of cities IN EACH STATE ordered by state abbreviation.
SELECT state_abbreviation, sum(population) AS sum_of_city_populations
FROM city
GROUP BY state_abbreviation
ORDER BY state_abbreviation;

-- The 10 smallest city populations IN EACH STATE ordered by city population.
SELECT state_abbreviation, min(population) AS min_population
FROM city
GROUP BY state_abbreviation
ORDER BY min_population
LIMIT 10;

-- Miscelleneous (PAGING THROUGH RESULTS)

-- While you can use LIMIT to limit the number of results returned by a query,
-- it's recommended to use OFFSET and FETCH if you want to get
-- "pages" of results.
-- For instance, to get the first 10 rows in the city table
-- ordered by the name, you could use the following query.
-- (Skip 0 rows, and return only the first 10 rows from the sorted result set.)
SELECT city_name, population
FROM city
ORDER BY city_name
OFFSET 20 ROWS FETCH NEXT 10 ROWS ONLY;


-- SUBQUERIES (optional) 

-- MOodify the following query to include state name rather than the state abbreviation 
-- while counting the number of cities in each state,
SELECT * FROM city;
SELECT * FROM state;


SELECT COUNT(city_name) AS cities, 
   (
      SELECT state_name FROM state WHERE state_abbreviation = c.state_abbreviation
   ) as state_name
FROM city AS c
GROUP BY state_abbreviation
ORDER BY cities DESC;



-- Modify the following to list just the capital cities for the states in the Northeast census region.
SELECT city_id, state_abbreviation, city_name
FROM city
WHERE city_id IN
  (
     SELECT capital FROM state WHERE census_region = 'Northeast'
  )

ORDER BY state_abbreviation;


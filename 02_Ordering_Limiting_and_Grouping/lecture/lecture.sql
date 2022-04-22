SELECT * FROM state;
SELECT * FROM park;
SELECT * FROM city;


-- ORDERING RESULTS

-- Populations of all states from largest to smallest.


-- States sorted alphabetically (A-Z) within their census region. The census regions are 
-- sorted in reverse alphabetical (Z-A) order.


-- The biggest park by area



-- LIMITING RESULTS 

-- The 10 largest cities by populations


-- THIS ONE USES DATES
-- The 20 oldest parks from oldest to youngest in years, sorted alphabetically by name.



-- CONCATENATING OUTPUTS (ONLY DO THIS IF YOU HAVE TO)

-- All city names and their state abbreviation.


-- The all parks by name and date established.


-- The census region and state name of all states in the West & Midwest sorted in ascending order.



-- AGGREGATE FUNCTIONS

-- Average population across all the states. Note the use of alias, common with aggregated values.


-- Total population in the West and South census regions


-- The number of cities with populations greater than 1 million


-- The number of state nicknames.


-- The area of the smallest and largest parks.



-- GROUP BY

-- Count the number of cities IN EACH STATE, ordered from most cities to least.


-- Determine the average park area depending upon WHETHER PARKS ALLOW CAMPING OR NOT.


-- Sum of the population of cities IN EACH STATE ordered by state abbreviation.


-- The smallest city population IN EACH STATE ordered by city population.



-- Miscelleneous (PAGING THROUGH RESULTS)

-- While you can use LIMIT to limit the number of results returned by a query,
-- it's recommended to use OFFSET and FETCH if you want to get
-- "pages" of results.
-- For instance, to get the first 10 rows in the city table
-- ordered by the name, you could use the following query.
-- (Skip 0 rows, and return only the first 10 rows from the sorted result set.)



-- SUBQUERIES (optional) 

-- MOodify the following query to include state name rather than the state abbreviation 
-- while counting the number of cities in each state,
SELECT * FROM city;
SELECT COUNT(city_name) AS cities, state_abbreviation
FROM city
GROUP BY state_abbreviation
ORDER BY cities DESC;



-- Modify the following to list just the capital cities for the states in the Northeast census region.
SELECT state_abbreviation, city_name
FROM city
ORDER BY state_abbreviation;


-- 5. The sum of the population of all cities in California. Name the column 'california_population'.
-- Expected answer is around 20,000,000
-- (1 row)
SELECT Sum(population) AS california_population
FROM city
WHERE state_abbreviation = 'CA'
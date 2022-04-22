-- 4. The average population of states in the "West" census region. Name the column 'average_population'.
-- Expected answer is around 6,000,000
-- (1 row)
SELECT Avg(population) AS average_population
FROM state
WHERE census_region = 'West'


-- p6) Retrieve the names and positions of all employees with salary > 50,000:
SELECT name, position
FROM employees
WHERE salary > 50000
ORDER BY salary DESC;

-- p6) Bonus: Top 3 highest-paid employees
SELECT name, position, salary
FROM employees
ORDER BY salary DESC
LIMIT 3;
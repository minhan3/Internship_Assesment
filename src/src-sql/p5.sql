-- p5) Average salary per department
SELECT department,
  ROUND(AVG(salary), 2) AS avg_salary
FROM employees
GROUP BY department
ORDER BY department;

-- p5) Bonus: Highest salary each department
SELECT e.department, e.name AS employee_name, ROUND(e.salary, 2) AS highest_salary
FROM employees e
WHERE e.salary = (
  SELECT MAX(salary)
  FROM employees
  WHERE department = e.department
)
ORDER BY e.department, e.name;
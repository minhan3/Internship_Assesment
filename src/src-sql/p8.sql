-- Query in Problem 8
SELECT *
FROM employees
WHERE department = 'Sales' AND salary > 50000;

-- propose an index or indexes to improve its performance.
CREATE INDEX idx_department_salary ON employees(department(20), salary);

-- The index on `(department(20), salary)` is chosen to make the query faster when searching by department and salary. 
-- It helps the database find results more quickly and avoids scanning the whole table, which is useful when there is a lot of data. 

-- However, using indexes has a drawbacks because:
--  1. Additional storage required
--     Indexes need extra space to store the index data. So, when you add an index,
--     it increasesthe size of the database because the database must store both the actual table and the index.
--  2. Slower write performance
--     Every time you insert, update, or delete data in the table, the index also needs to be updated.
--     This extra work can slow down write operations, especially if there are many indexes or too many changes

-- So, we must balance between faster searches and the extra cost of storage and slower data changes during operations
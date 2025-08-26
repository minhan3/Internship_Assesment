USE asd_interview;

-- p1) Orders from customers in 'New York'
SELECT c.customer_name, o.order_date, o.total_amount
FROM customers c
JOIN orders o   ON o.customer_id = c.customer_id
WHERE c.city = 'New York'
ORDER BY o.order_date;

-- p1) Bonus: include avg order amount per New York customer
SELECT c.customer_name, o.order_date, o.total_amount,
	   Round(AVG(o.total_amount) OVER (PARTITION BY c.customer_id), 2) AS avg_total_amount
FROM customers c
JOIN orders o ON o.customer_id = c.customer_id
WHERE c.city = 'New York'
ORDER BY o.order_date;
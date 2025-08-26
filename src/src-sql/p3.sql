-- p3) Calculate the total quantity sold and the avg quantity sold per order.
SELECT SUM(s.quantity) AS total_quantity_sold, ROUND(AVG(s.quantity), 2) AS avg_quantity_per_order
FROM sales s;

-- p3) Bonus: include the totsales amount and avgsales amount per order.
SELECT SUM(s.quantity) AS total_quantity_sold, ROUND(AVG(s.quantity), 2) AS avg_quantity_per_order,
       ROUND(SUM(s.quantity * i.price), 2) AS total_sales_amount,
       ROUND(AVG(s.quantity * i.price), 2) AS avg_sales_amount_per_order
FROM sales s
JOIN inventories i ON s.product_id = i.product_id;
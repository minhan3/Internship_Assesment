-- p7) Delete customer by ID + related records
DELETE FROM customers
WHERE customer_id = 7;

-- To check the latest one
Select *
From customers;
Select *
From sales;
Select *
From orders;

-- only for p7
ALTER TABLE orders
DROP FOREIGN KEY fk_orders_customer;

ALTER TABLE orders
ADD CONSTRAINT fk_orders_customer
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
ON DELETE CASCADE;

ALTER TABLE sales
DROP FOREIGN KEY fk_sales_customer;

ALTER TABLE sales
ADD CONSTRAINT fk_sales_customer
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
ON DELETE CASCADE;

ALTER TABLE sales
DROP FOREIGN KEY fk_sales_order;

ALTER TABLE sales
ADD CONSTRAINT fk_sales_order
FOREIGN KEY (order_id) REFERENCES orders(order_id)
ON DELETE CASCADE;
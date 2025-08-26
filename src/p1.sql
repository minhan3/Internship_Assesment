USE asd_interview;

-- P1) Table Creation

-- Drop tables if they already exist (for reruns)
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS inventories;

CREATE TABLE employees (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name TEXT NOT NULL,
    position TEXT,
    department TEXT,
    salary NUMERIC(10,2)
);

CREATE TABLE customers (
    customer_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    customer_name TEXT NOT NULL,
    city TEXT
);

CREATE TABLE orders (
    order_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    order_date DATE,
    customer_id INTEGER,
    total_amount NUMERIC(12,2),
    CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE inventories (
    product_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    product_name TEXT NOT NULL,
    quantity INTEGER,
    price NUMERIC(10,2)
);

CREATE TABLE sales (
    order_id INTEGER,
    customer_id INTEGER,
    product_id INTEGER,
    quantity INTEGER,
    sale_date DATE,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_sales_order FOREIGN KEY (order_id) REFERENCES orders(order_id),
    CONSTRAINT fk_sales_customer FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    CONSTRAINT fk_sales_product FOREIGN KEY (product_id) REFERENCES inventories(product_id)
);


-- Dummy Data Inserts 
INSERT INTO employees (name, position, department, salary) VALUES
('Alice', 'Manager', 'HR', 6000.00),
('Bob', 'Engineer', 'IT', 5000.00),
('Charlie', 'Technician', 'Maintenance', 3500.00),
('David', 'Analyst', 'Finance', 4200.00),
('Emma', 'Clerk', 'HR', 2800.00),
('Farah', 'Engineer', 'IT', 5200.00),
('Justin', 'Supervisor', 'Maintenance', 3900.00),
('Yamin', 'CEO', 'Accounting', 84000.00),
('Pak Abu', 'President', 'Marketing', 58000.00),
('John', 'COO', 'Sales', 87000.00),
('Lea', 'CEO', 'Sales', 95000.00);

INSERT INTO customers (customer_name, city) VALUES
('Ahmad', 'New York'),
('Salmah', 'Los Angeles'),
('Pak Ali', 'Kuala Lumpur'),
('Maria Lopez', 'New York'),
('Hiro Tanaka', 'Tokyo'),
('Fatima Khan', 'Dubai'),
('Nabhan', 'Penang');

INSERT INTO inventories (product_name, quantity, price) VALUES
('Laptop', 50, 1200.00),
('Phone', 100, 800.00),
('Tablet', 70, 600.00),
('Monitor', 40, 300.00),
('Keyboard', 200, 50.00),
('Mouse', 250, 25.00),
('Printer', 20, 400.00),
('Headphones', 150, 90.00);

INSERT INTO orders (order_date, customer_id, total_amount) VALUES
('2025-08-01', 1, 2400.00),  -- Ahmad
('2025-08-02', 2, 800.00),   -- Salmah
('2025-08-03', 4, 1500.00),  -- Maria Lopez
('2025-08-04', 1, 500.00),   -- Ahmad again
('2025-08-05', 3, 2000.00),  -- Pak Ali
('2025-08-06', 7, 900.00);   -- Nabhan

INSERT INTO sales (order_id, customer_id, product_id, quantity, sale_date) VALUES
(1, 1, 1, 2, '2025-08-01'),   -- Ahmad bought 2 laptops
(2, 2, 2, 1, '2025-08-02'),   -- Salmah bought 1 phone
(3, 4, 3, 2, '2025-08-03'),   -- Maria Lopez bought 2 tablets
(4, 1, 5, 5, '2025-08-04'),   -- Ahmad bought 5 keyboards
(5, 3, 2, 2, '2025-08-05'),   -- Pak Ali bought 2 phones
(6, 7, 8, 3, '2025-08-06'),   -- Nabhan bought 3 headphones
(5, 3, 4, 1, '2025-08-05');   -- Pak Ali also bought 1 monitor in same order

-- to reset the dummy data
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE sales;
TRUNCATE TABLE orders;
TRUNCATE TABLE inventories;
TRUNCATE TABLE customers;
TRUNCATE TABLE employees;
SET FOREIGN_KEY_CHECKS = 1;

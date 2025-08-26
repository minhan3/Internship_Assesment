-- p4) Update the price of a specific product by specifying the product_id.
UPDATE inventories
SET price = 1500.00
WHERE product_id = 1;

SELECT * FROM inventories; -- to check lepas update

-- p4) Bonus: Increase the price of all products by 10%
UPDATE inventories
SET price = price + (price * 0.10)
WHERE product_id > 0;

SELECT * FROM inventories; -- to check lepas update
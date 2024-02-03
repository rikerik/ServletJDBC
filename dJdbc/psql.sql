-- Create users table if not exists
CREATE TABLE IF NOT EXISTS users (
    user_id serial PRIMARY KEY,
    user_name VARCHAR(255)
);

-- Create products table
CREATE TABLE IF NOT EXISTS products (
    product_id serial PRIMARY KEY,
    product_name VARCHAR(255),
    supplier VARCHAR(255),
    price NUMERIC(10,2),
    quantity INTEGER,
    created_by_user_id INTEGER REFERENCES users(user_id),
    created_on TIMESTAMP,
    modified_by_user_id INTEGER REFERENCES users(user_id),
    modified_on TIMESTAMP
);

-- Create a function for setting created and modified timestamps
CREATE OR REPLACE FUNCTION set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        NEW.created_on := NOW();
        NEW.modified_on := NOW();
    ELSIF (TG_OP = 'UPDATE') THEN
        NEW.modified_on := NOW();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create triggers for setting timestamps
CREATE TRIGGER set_timestamp_on_insert
BEFORE INSERT ON products
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();

CREATE TRIGGER set_timestamp_on_update
BEFORE UPDATE ON products
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();

-- Insert data
INSERT INTO users (user_name) VALUES
('User1'),
('User2');

INSERT INTO products (
    product_id, product_name, supplier, price, quantity,
    created_by_user_id, modified_by_user_id
) VALUES 
(1, 'Test Product1', 'Sample Supplier', 21.0, 10, 1, 2),
(2, 'Test Product2', 'Sample Supplier2', 43.0, 130, 1, 2);
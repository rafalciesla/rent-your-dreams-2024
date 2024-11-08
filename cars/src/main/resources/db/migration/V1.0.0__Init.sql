CREATE SEQUENCE IF NOT EXISTS cars_seq START WITH 100 INCREMENT 1;

CREATE TABLE IF NOT EXISTS cars (
    id BIGINT NOT NULL DEFAULT nextval('cars_seq') PRIMARY KEY,
    brand VARCHAR(255),
    model VARCHAR(255),
    year_of_manufacture INT,
    engine_type VARCHAR(20),
    engine_capacity INT,
    price DECIMAL,
    created_at TIMESTAMP,
    created_by VARCHAR(255),
    last_updated_at TIMESTAMP,
    last_updated_by VARCHAR(255)
);

INSERT INTO cars (brand, model, year_of_manufacture, engine_type, engine_capacity, price, created_at)
VALUES ('Chevrolet', 'Corvette', 2015, 'V8', 6200, 2500.00, now());
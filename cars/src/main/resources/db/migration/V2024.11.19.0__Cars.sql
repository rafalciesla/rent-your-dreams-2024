CREATE SEQUENCE IF NOT EXISTS cars_seq START WITH 100 INCREMENT 1;

CREATE TABLE IF NOT EXISTS cars
(
    id                  BIGINT NOT NULL DEFAULT nextval('cars_seq') PRIMARY KEY,
    brand               VARCHAR(255),
    model               VARCHAR(255),
    year_of_manufacture INTEGER,
    engine_type         VARCHAR(255),
    engine_layout       VARCHAR(255),
    transmission_type   VARCHAR(255),
    engine_capacity     INTEGER,
    horse_power         INTEGER,
    time_to100          FLOAT,
    number_of_doors     INTEGER,
    number_of_seats     INTEGER,
    color               VARCHAR(255),
    price_per_day       DECIMAL,
    description         TEXT,
    created_at          TIMESTAMP,
    created_by          VARCHAR(255),
    last_updated_at     TIMESTAMP,
    last_updated_by     VARCHAR(255)
);

INSERT INTO cars (brand,
                  model,
                  year_of_manufacture,
                  engine_type,
                  engine_layout,
                  transmission_type,
                  engine_capacity,
                  horse_power,
                  time_to100,
                  number_of_doors,
                  number_of_seats,
                  color,
                  price_per_day,
                  description,
                  created_at,
                  created_by)
VALUES ('Chevrolet',
        'Corvette',
        2015,
        'PETROL',
        'V8',
        'AUTOMATIC',
        6200,
        482,
        4.5,
        2,
        2,
        'WHITE',
        1600.00,
        'The Chevrolet Corvette C7 is a powerful and stylish sports car, perfect for those who crave speed and performance.
              With its sleek, aerodynamic design, the C7 combines a robust 6.2L V8 engine and advanced handling features, delivering an exhilarating driving experience.
              This iconic American sports car offers a luxurious yet driver-focused interior with premium materials, modern tech, and a comfortable cockpit feel.
              Ideal for both scenic cruises and dynamic drives, the Corvette C7 brings excitement and sophistication to every journey.
              Rent the Corvette C7 to enjoy a blend of power, precision, and unmistakable style.',
        now(),
        'Admin');
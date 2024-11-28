CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 100 INCREMENT 1;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL DEFAULT nextval('users_seq') PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    created_at TIMESTAMP DEFAULT now(),
    created_by VARCHAR(255),
    last_updated_at TIMESTAMP,
    last_updated_by VARCHAR(255)
);

INSERT INTO users (username, email, password)
VALUES ('mcrev', 'ra.ciesla@wp.pl', '997');
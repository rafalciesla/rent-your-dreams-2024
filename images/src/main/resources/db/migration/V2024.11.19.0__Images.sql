CREATE SEQUENCE IF NOT EXISTS images_seq START WITH 100 INCREMENT 1;

CREATE TABLE IF NOT EXISTS images
(
    id               BIGINT NOT NULL DEFAULT nextval('images_seq') PRIMARY KEY,
    file_name        VARCHAR(255),
    file_path        VARCHAR(255),
    file_download_id UUID,
    created_at       TIMESTAMP,
    created_by       VARCHAR(255),
    last_updated_at  TIMESTAMP,
    last_updated_by  VARCHAR(255)
);

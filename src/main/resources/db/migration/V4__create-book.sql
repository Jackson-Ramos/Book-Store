CREATE TABLE IF NOT EXISTS books (
    id           VARCHAR(36) PRIMARY KEY,
    title        VARCHAR(100) NOT NULL UNIQUE,
    publisher_id VARCHAR(36),
    CONSTRAINT fk_publisher
        FOREIGN KEY (publisher_id)
            REFERENCES publishers (id)
)
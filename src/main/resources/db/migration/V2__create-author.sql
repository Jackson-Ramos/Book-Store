CREATE TABLE IF NOT EXISTS authors(
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);
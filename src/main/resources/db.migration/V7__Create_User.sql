CREATE TABLE IF NOT EXISTS users
(
    id                      VARCHAR(36) PRIMARY KEY,
    user_name               VARCHAR(255) NOT NULL,
    password                VARCHAR(255) NOT NULL,
);

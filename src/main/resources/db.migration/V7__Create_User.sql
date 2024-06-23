CREATE TABLE IF NOT EXISTS users
(
    id                      VARCHAR(36) PRIMARY KEY,
    user_name               VARCHAR(255) NOT NULL,
    full_name               VARCHAR(255) NOT NULL,
    password                VARCHAR(255) NOT NULL,
    account_non_expired     BOOLEAN      NOT NULL,
    account_non_locked      BOOLEAN      NOT NULL,
    credentials_non_expired BOOLEAN      NOT NULL,
    enabled                 BOOLEAN      NOT NULL
);

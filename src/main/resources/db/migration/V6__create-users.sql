CREATE TABLE users
(
    id        VARCHAR(36)  NOT NULL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL UNIQUE,
    password  VARCHAR(20) NOT NULL,
    role      VARCHAR(20)  NOT NULL
);
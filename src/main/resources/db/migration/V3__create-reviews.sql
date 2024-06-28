CREATE TABLE IF NOT EXISTS reviews(
    id      VARCHAR(36) PRIMARY KEY,
    comment TEXT NOT NULL,
    book_id VARCHAR(36),
    CONSTRAINT fk_book_review
        FOREIGN KEY (book_id)
            REFERENCES books (id)
);

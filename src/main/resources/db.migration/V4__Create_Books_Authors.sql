CREATE TABLE IF NOT EXISTS books_authors
(
    book_id   VARCHAR(36),
    author_id VARCHAR(36),
    PRIMARY KEY (book_id, author_id),
    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
            REFERENCES books (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
            REFERENCES authors (id)
            ON DELETE CASCADE
);

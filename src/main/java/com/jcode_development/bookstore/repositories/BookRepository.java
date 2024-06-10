package com.jcode_development.bookstore.repositories;

import com.jcode_development.bookstore.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	Book findBookByTitleOrId(String title, String id);
}

package com.jcode_development.bookstore.services;

import com.jcode_development.bookstore.repositories.AuthorRepository;
import com.jcode_development.bookstore.repositories.BookRepository;
import com.jcode_development.bookstore.repositories.PublisherRepository;
import com.jcode_development.bookstore.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	private final ReviewRepository reviewRepository;
	
	public BookService(
			AuthorRepository authorRepository,
			BookRepository bookRepository,
			PublisherRepository publisherRepository,
			ReviewRepository reviewRepository
	) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
		this.reviewRepository = reviewRepository;
	}
}

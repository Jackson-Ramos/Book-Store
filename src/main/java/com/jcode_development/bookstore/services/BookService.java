package com.jcode_development.bookstore.services;

import com.jcode_development.bookstore.model.book.Book;
import com.jcode_development.bookstore.model.book.BookRequest;
import com.jcode_development.bookstore.model.review.Review;
import com.jcode_development.bookstore.repositories.AuthorRepository;
import com.jcode_development.bookstore.repositories.BookRepository;
import com.jcode_development.bookstore.repositories.PublisherRepository;
import com.jcode_development.bookstore.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	
	public BookService(
			AuthorRepository authorRepository,
			BookRepository bookRepository,
			PublisherRepository publisherRepository
	) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
		
	}
	
	@Transactional
	public ResponseEntity<Void> createBook(BookRequest bookRequest) {
		Book book = new Book();
		book.setTitle(bookRequest.title());
		book.setPublisher(publisherRepository.findById(bookRequest.publisherId()).orElse(null));
		book.setAuthors(new HashSet<>(authorRepository.findAllById(bookRequest.authorsIds())));
		
		Review review = new Review();
		review.setComment(bookRequest.review());
		review.setBook(book);
		book.setReview(review);
		
		bookRepository.save(book);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Set<Book>> getBooks(){
		return ResponseEntity.ok(new HashSet<>(bookRepository.findAll()));
	}
	
}

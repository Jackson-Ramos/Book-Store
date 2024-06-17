package com.jcode_development.bookstore.services;

import com.jcode_development.bookstore.controllers.BookController;
import com.jcode_development.bookstore.mapper.Mapper;
import com.jcode_development.bookstore.model.book.Book;
import com.jcode_development.bookstore.model.book.BookRequest;
import com.jcode_development.bookstore.model.book.BookResponse;
import com.jcode_development.bookstore.model.review.Review;
import com.jcode_development.bookstore.repositories.AuthorRepository;
import com.jcode_development.bookstore.repositories.BookRepository;
import com.jcode_development.bookstore.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
	
	public ResponseEntity<Set<BookResponse>> getBooks() {
		var books = Mapper.parseObjects(bookRepository.findAll(), BookResponse.class);
		for (BookResponse bookResponse : books) {
			bookResponse.add(linkTo(methodOn(BookController.class).findById(bookResponse.getId())).withSelfRel());
		}
		return ResponseEntity.ok(books);
	}
	
	public ResponseEntity<BookResponse> getBook(String id) {
		var book = Mapper.parseObject(
				bookRepository.findById(id).orElseThrow(RuntimeException::new),
				BookResponse.class);
		book.add(linkTo(methodOn(BookController.class).findAll()).withRel("All books"));
		return ResponseEntity.ok(book);
	}
	
	@Transactional
	public ResponseEntity<Void> deleteBook(String id) {
		var book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
		bookRepository.delete(book);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	public ResponseEntity<Void> updateBook(String id, BookRequest bookRequest) {
		var book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
		book.setTitle(bookRequest.title());
		book.setPublisher(publisherRepository.findById(bookRequest.publisherId()).orElse(null));
		book.setAuthors(new HashSet<>(authorRepository.findAllById(bookRequest.authorsIds())));
		
		if (book.getReview() != null) {
			book.getReview().setComment(bookRequest.review());
		} else {
			Review newReview = new Review();
			newReview.setComment(bookRequest.review());
			newReview.setBook(book);
			book.setReview(newReview);
		}
		bookRepository.save(book);
		return ResponseEntity.ok().build();
	}
}

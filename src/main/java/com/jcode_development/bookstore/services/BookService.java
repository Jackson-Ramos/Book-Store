package com.jcode_development.bookstore.services;

import com.jcode_development.bookstore.controllers.BookController;
import com.jcode_development.bookstore.exceptions.ResourceNotFound;
import com.jcode_development.bookstore.mapper.Mapper;
import com.jcode_development.bookstore.model.book.Book;
import com.jcode_development.bookstore.model.book.BookRequest;
import com.jcode_development.bookstore.model.book.BookResponse;
import com.jcode_development.bookstore.model.review.Review;
import com.jcode_development.bookstore.repositories.AuthorRepository;
import com.jcode_development.bookstore.repositories.BookRepository;
import com.jcode_development.bookstore.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	private final PagedResourcesAssembler<BookResponse> assembler;
	
	
	public BookService(
			AuthorRepository authorRepository,
			BookRepository bookRepository,
			PublisherRepository publisherRepository,
			PagedResourcesAssembler<BookResponse> assembler) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
		this.assembler = assembler;
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
	
	public PagedModel<EntityModel<BookResponse>> getBooks(Pageable pageable) {
		
		var bookPage = bookRepository.findAll(pageable);
		var bookResponsePage = bookPage.map(b -> Mapper.parseObject(b, BookResponse.class));
		
		bookResponsePage.forEach(book -> book.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel()));
		
		Link link = linkTo(
				methodOn(BookController.class)
						.findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
		return assembler.toModel(bookResponsePage, link);
	}
	
	public ResponseEntity<BookResponse> getBook(String id) {
		var book = Mapper.parseObject(
				bookRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Id: " + id + " Not Found")),
				BookResponse.class);
		book.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel());
		return ResponseEntity.ok(book);
	}
	
	@Transactional
	public ResponseEntity<Void> deleteBook(String id) {
		var book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Id: " + id + " Not Found"));
		bookRepository.delete(book);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	public ResponseEntity<Void> updateBook(String id, BookRequest bookRequest) {
		var book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Id: " + id + " Not Found"));
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

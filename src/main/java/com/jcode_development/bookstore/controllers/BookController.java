package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.book.BookRequest;
import com.jcode_development.bookstore.model.book.BookResponse;
import com.jcode_development.bookstore.services.BookService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/store/book")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<Void> save(@RequestBody BookRequest bookRequest) {
		return bookService.createBook(bookRequest);
	}
	
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<Set<BookResponse>> findAll() {
		return bookService.getBooks();
	}
	
	@GetMapping(
			value = "/{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<BookResponse> findById(@PathVariable String id) {
		return bookService.getBook(id);
	}
	
	@PutMapping(
			value = "/{id}",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody BookRequest bookRequest) {
		return bookService.updateBook(id, bookRequest);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		return bookService.deleteBook(id);
	}
}

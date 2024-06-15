package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.book.BookRequest;
import com.jcode_development.bookstore.model.book.BookResponse;
import com.jcode_development.bookstore.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/store/book")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody BookRequest bookRequest) {
		return bookService.createBook(bookRequest);
	}
	
	@GetMapping
	public ResponseEntity<Set<BookResponse>> findAll() {
		return bookService.getBooks();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookResponse> findById(@PathVariable String id) {
		return bookService.getBook(id);
	}
}

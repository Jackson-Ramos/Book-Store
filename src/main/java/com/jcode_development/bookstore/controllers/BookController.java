package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.book.BookRequest;
import com.jcode_development.bookstore.model.book.BookResponse;
import com.jcode_development.bookstore.services.BookService;
import com.jcode_development.bookstore.swagger.BookDocumentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/book")
public class BookController implements BookDocumentation {
	
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
	public ResponseEntity<PagedModel<EntityModel<BookResponse>>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
		return ResponseEntity.ok(bookService.getBooks(pageable));
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

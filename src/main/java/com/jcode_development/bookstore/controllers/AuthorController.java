package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.author.AuthorRequest;
import com.jcode_development.bookstore.model.author.AuthorResponse;
import com.jcode_development.bookstore.services.AuthorService;
import com.jcode_development.bookstore.swagger.AuthorDocumentation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/store/author")
public class AuthorController implements AuthorDocumentation {
	
	private final AuthorService authorService;
	
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<Void> save(@RequestBody AuthorRequest authorRequest) {
		return authorService.createAuthor(authorRequest);
	}
	
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<Set<AuthorResponse>> findAll() {
		return authorService.getAuthors();
	}
	
	@GetMapping(
			value = "/{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<AuthorResponse> findById(@PathVariable String id) {
		return authorService.getAuthor(id);
	}
	
	@PutMapping(
			value = "/{id}",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody AuthorRequest authorRequest) {
		return authorService.updateAuthor(id, authorRequest);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		return authorService.deleteAuthor(id);
	}
}

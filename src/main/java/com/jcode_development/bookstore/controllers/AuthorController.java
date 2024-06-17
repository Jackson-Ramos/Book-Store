package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.author.AuthorRequest;
import com.jcode_development.bookstore.model.author.AuthorResponse;
import com.jcode_development.bookstore.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/store/author")
public class AuthorController {
	
	private final AuthorService authorService;
	
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody AuthorRequest authorRequest) {
		return authorService.createAuthor(authorRequest);
	}
	
	@GetMapping
	public ResponseEntity<Set<AuthorResponse>> findAll() {
		return authorService.getAuthors();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthorResponse> findById(@PathVariable String id) {
		return authorService.getAuthor(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody AuthorRequest authorRequest){
		return authorService.updateAuthor(id, authorRequest);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		return authorService.deleteAuthor(id);
	}
}

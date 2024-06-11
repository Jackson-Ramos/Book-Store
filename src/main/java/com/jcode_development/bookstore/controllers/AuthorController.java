package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.author.Author;
import com.jcode_development.bookstore.model.author.AuthorRequest;
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
	public ResponseEntity<Void> save(@RequestBody AuthorRequest authorRequest){
		return authorService.createAuthor(authorRequest);
	}
	
	@GetMapping
	public ResponseEntity<Set<Author>> findAll(){
		return authorService.getAuthors();
	}
}

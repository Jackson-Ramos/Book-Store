package com.jcode_development.bookstore.services;

import com.jcode_development.bookstore.controllers.AuthorController;
import com.jcode_development.bookstore.mapper.Mapper;
import com.jcode_development.bookstore.model.author.Author;
import com.jcode_development.bookstore.model.author.AuthorRequest;
import com.jcode_development.bookstore.model.author.AuthorResponse;
import com.jcode_development.bookstore.repositories.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AuthorService {
	
	private final AuthorRepository authorRepository;
	
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	public ResponseEntity<Void> createAuthor(AuthorRequest authorRequest) {
		Author author = new Author();
		author.setName(authorRequest.name());
		author.setBooks(new HashSet<>());
		
		authorRepository.save(author);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Set<AuthorResponse>> getAuthors() {
		var authors = Mapper.parseObjects(authorRepository.findAll(), AuthorResponse.class);
		authors.forEach(
				authorResponse -> authorResponse
						.add(linkTo(methodOn(AuthorController.class)
								.findById(authorResponse.getId()))
								.withSelfRel()));
		return ResponseEntity.ok(authors);
	}
	
	public ResponseEntity<AuthorResponse> getAuthor(String id) {
		var author = authorRepository.findById(id).orElseThrow(RuntimeException::new);
		return ResponseEntity.ok(
				Mapper.parseObject(author, AuthorResponse.class)
						.add(linkTo(methodOn(AuthorController.class).findAll()).withSelfRel())
		);
	}
	
	public ResponseEntity<Void> updateAuthor(String id, AuthorRequest authorRequest) {
		var author = authorRepository.findById(id).orElseThrow(RuntimeException::new);
		author.setName(authorRequest.name());
		authorRepository.save(author);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> deleteAuthor(String id) {
		var author = authorRepository.findById(id).orElseThrow(RuntimeException::new);
		authorRepository.delete(author);
		return ResponseEntity.noContent().build();
	}
}
package com.jcode_development.bookstore.services;

import com.jcode_development.bookstore.model.author.Author;
import com.jcode_development.bookstore.model.author.AuthorRequest;
import com.jcode_development.bookstore.model.book.Book;
import com.jcode_development.bookstore.repositories.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorService {
	
	private final AuthorRepository authorRepository;
	
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	public ResponseEntity<Void> createAuthor(AuthorRequest authorRequest){
		Author author = new Author();
		author.setName(authorRequest.name());
		author.setBooks(new HashSet<>());
		
		authorRepository.save(author);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Set<Author>> getAuthors(){
		return ResponseEntity.ok(new HashSet<>(authorRepository.findAll()));
	}
}

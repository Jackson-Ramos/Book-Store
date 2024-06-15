package com.jcode_development.bookstore.services;

import com.jcode_development.bookstore.model.publisher.Publisher;
import com.jcode_development.bookstore.model.publisher.PublisherRequest;
import com.jcode_development.bookstore.repositories.PublisherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PublisherService {
	
	private final PublisherRepository publisherRepository;
	
	public PublisherService(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}
	
	public ResponseEntity<Void> savePublisher(PublisherRequest publisherRequest) {
		Publisher publisher = new Publisher();
		publisher.setName(publisherRequest.name());
		publisher.setBooks(new HashSet<>());
		
		publisherRepository.save(publisher);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	public ResponseEntity<Set<Publisher>> getPublishers() {
		return ResponseEntity.ok(new HashSet<>(publisherRepository.findAll()));
	}
}

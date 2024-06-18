package com.jcode_development.bookstore.services;

import com.jcode_development.bookstore.mapper.Mapper;
import com.jcode_development.bookstore.model.publisher.Publisher;
import com.jcode_development.bookstore.model.publisher.PublisherRequest;
import com.jcode_development.bookstore.model.publisher.PublisherResponse;
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
	
	public ResponseEntity<PublisherResponse> getPublisher(String id) {
		var publisher = publisherRepository.findById(id).orElseThrow(RuntimeException::new);
		return ResponseEntity.ok(Mapper.parseObject(publisher, PublisherResponse.class));
	}
	
	public ResponseEntity<Void> updatePublisher(String id, PublisherRequest publisherRequest) {
		var publisher = publisherRepository.findById(id).orElseThrow(RuntimeException::new);
		publisher.setName(publisherRequest.name());
		publisherRepository.save(publisher);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> delete(String id) {
		var publisher = publisherRepository.findById(id).orElseThrow(RuntimeException::new);
		publisherRepository.delete(publisher);
		return ResponseEntity.noContent().build();
	}
	
}

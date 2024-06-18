package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.publisher.Publisher;
import com.jcode_development.bookstore.model.publisher.PublisherRequest;
import com.jcode_development.bookstore.model.publisher.PublisherResponse;
import com.jcode_development.bookstore.services.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/store/publisher")
public class PublisherController {
	
	private final PublisherService publisherService;
	
	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody PublisherRequest publisherRequest) {
		return publisherService.savePublisher(publisherRequest);
	}
	
	@GetMapping
	public ResponseEntity<Set<Publisher>> findAll() {
		return publisherService.getPublishers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PublisherResponse> findById(@PathVariable String id) {
		return publisherService.getPublisher(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody PublisherRequest publisherRequest) {
		return publisherService.updatePublisher(id, publisherRequest);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		return publisherService.delete(id);
	}
	
}

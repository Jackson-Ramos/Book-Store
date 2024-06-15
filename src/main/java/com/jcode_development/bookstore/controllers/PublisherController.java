package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.publisher.Publisher;
import com.jcode_development.bookstore.model.publisher.PublisherRequest;
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
}

package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.publisher.Publisher;
import com.jcode_development.bookstore.model.publisher.PublisherRequest;
import com.jcode_development.bookstore.model.publisher.PublisherResponse;
import com.jcode_development.bookstore.services.PublisherService;
import com.jcode_development.bookstore.swagger.PublisherDocumentation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/store/publisher")
public class PublisherController implements PublisherDocumentation {
	
	private final PublisherService publisherService;
	
	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}
	
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<Void> save(@RequestBody PublisherRequest publisherRequest) {
		return publisherService.savePublisher(publisherRequest);
	}
	
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<Set<PublisherResponse>> findAll() {
		return publisherService.getPublishers();
	}
	
	@GetMapping(
			value = "/{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<PublisherResponse> findById(@PathVariable String id) {
		return publisherService.getPublisher(id);
	}
	
	@PutMapping(
			value = "/{id}",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody PublisherRequest publisherRequest) {
		return publisherService.updatePublisher(id, publisherRequest);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		return publisherService.delete(id);
	}
	
}

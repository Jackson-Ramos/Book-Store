package com.jcode_development.bookstore.services;

import com.jcode_development.bookstore.controllers.PublisherController;
import com.jcode_development.bookstore.exceptions.ResourceNotFound;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
	
	public ResponseEntity<Set<PublisherResponse>> getPublishers() {
		var publishers = Mapper.parseObjects(publisherRepository.findAll(), PublisherResponse.class);
		for(PublisherResponse response: publishers){
			response.add(linkTo(methodOn(PublisherController.class).findById(response.getId())).withSelfRel());
		}
		return ResponseEntity.ok(publishers);
	}
	
	public ResponseEntity<PublisherResponse> getPublisher(String id) {
		var publisher = publisherRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Id: " + id + " Not Found"));
		return ResponseEntity.ok(
				Mapper.parseObject(publisher, PublisherResponse.class)
						.add(linkTo(methodOn(PublisherController.class).findAll()).withRel("All publishes"))
		);
	}
	
	public ResponseEntity<Void> updatePublisher(String id, PublisherRequest publisherRequest) {
		var publisher = publisherRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Id: " + id + " Not Found"));
		publisher.setName(publisherRequest.name());
		publisherRepository.save(publisher);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> delete(String id) {
		var publisher = publisherRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Id: " + id + " Not Found"));
		publisherRepository.delete(publisher);
		return ResponseEntity.noContent().build();
	}
	
}

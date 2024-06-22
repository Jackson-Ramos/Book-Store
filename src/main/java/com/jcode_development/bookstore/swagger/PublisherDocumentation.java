package com.jcode_development.bookstore.swagger;

import com.jcode_development.bookstore.model.publisher.PublisherRequest;
import com.jcode_development.bookstore.model.publisher.PublisherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Set;

@Tag(name = "Publishers", description = "Operations related to publishers in the bookstore")
public interface PublisherDocumentation {
	
	@Operation(
			summary = "Save a new publisher",
			description = "Save a new publisher with JSON or XML payload",
			responses = {
					@ApiResponse(description = "Created", responseCode = "201", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Void> save(PublisherRequest publisherRequest);
	
	@Operation(
			summary = "Find all publishers",
			description = "Retrieve a list of all publishers",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = PublisherResponse.class))
									),
									@Content(
											mediaType = "application/xml",
											array = @ArraySchema(schema = @Schema(implementation = PublisherResponse.class))
									)
							}
					),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Set<PublisherResponse>> findAll();
	
	@Operation(
			summary = "Find publisher by ID",
			description = "Retrieve a single publisher by its ID",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											schema = @Schema(implementation = PublisherResponse.class)
									),
									@Content(
											mediaType = "application/xml",
											schema = @Schema(implementation = PublisherResponse.class)
									)
							}
					),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<PublisherResponse> findById(String id);
	
	@Operation(
			summary = "Update a publisher",
			description = "Update an existing publisher with JSON or XML payload",
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Void> update(String id, PublisherRequest publisherRequest);
	
	@Operation(
			summary = "Delete a publisher",
			description = "Delete a publisher by its ID",
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Void> delete(String id);
}

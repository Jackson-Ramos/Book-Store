package com.jcode_development.bookstore.swagger;

import com.jcode_development.bookstore.model.author.AuthorRequest;
import com.jcode_development.bookstore.model.author.AuthorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Set;

@Tag(name = "Authors", description = "Operations related to authors in the bookstore")
public interface AuthorDocumentation {
	
	@Operation(
			summary = "Find all authors",
			description = "Retrieve a list of all authors",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = AuthorResponse.class))
									),
									@Content(
											mediaType = "application/xml",
											array = @ArraySchema(schema = @Schema(implementation = AuthorResponse.class))
									)
							}
					),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Set<AuthorResponse>> findAll();
	
	@Operation(
			summary = "Find author by ID",
			description = "Retrieve a single author by their ID",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											schema = @Schema(implementation = AuthorResponse.class)
									),
									@Content(
											mediaType = "application/xml",
											schema = @Schema(implementation = AuthorResponse.class)
									)
							}
					),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<AuthorResponse> findById(String id);
	
	@Operation(
			summary = "Save a new author",
			description = "Save a new author with JSON or XML payload",
			responses = {
					@ApiResponse(description = "Created", responseCode = "201", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Void> save(AuthorRequest authorRequest);
	
	@Operation(
			summary = "Update an author",
			description = "Update an existing author with JSON or XML payload",
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Void> update(String id, AuthorRequest authorRequest);
	
	@Operation(
			summary = "Delete an author",
			description = "Delete an author by their ID",
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Void> delete(String id);
}

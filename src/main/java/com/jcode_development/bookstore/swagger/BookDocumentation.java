package com.jcode_development.bookstore.swagger;

import com.jcode_development.bookstore.model.book.BookRequest;
import com.jcode_development.bookstore.model.book.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@Tag(name = "Books", description = "Operations related to books in the bookstore")
public interface BookDocumentation {
	
	@Operation(
			summary = "Find all books",
			description = "Retrieve a list of all books",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = BookResponse.class))
									),
									@Content(
											mediaType = "application/xml",
											array = @ArraySchema(schema = @Schema(implementation = BookResponse.class))
									)
							}
					),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<PagedModel<EntityModel<BookResponse>>> findAll(Integer page, Integer limit, String direction);
	
	@Operation(
			summary = "Find book by ID",
			description = "Retrieve a single book by its ID",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											schema = @Schema(implementation = BookResponse.class)
									),
									@Content(
											mediaType = "application/xml",
											schema = @Schema(implementation = BookResponse.class)
									)
							}
					),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<BookResponse> findById(String id);
	
	@Operation(
			summary = "Save a new book",
			description = "Save a new book with JSON or XML payload",
			responses = {
					@ApiResponse(description = "Created", responseCode = "201", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Void> save(BookRequest bookRequest);
	
	@Operation(
			summary = "Update a book",
			description = "Update an existing book with JSON or XML payload",
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	ResponseEntity<Void> update(String id, BookRequest bookRequest);
	
	@Operation(
			summary = "Delete a book",
			description = "Delete a book by its ID",
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Void> delete(String id);
}

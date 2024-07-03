package com.jcode_development.bookstore.swagger;

import com.jcode_development.bookstore.model.user.security.AccountCredentials;
import com.jcode_development.bookstore.model.user.security.RegisterCredentials;
import com.jcode_development.bookstore.model.user.security.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth", description = "Operations related to authentication and authorization")
public interface AuthenticationDocumentation {
	
	@Operation(
			summary = "Login in Book Store",
			description = "Login user and return token",
			responses = {
					@ApiResponse(
							description = "Success",
							responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											schema = @Schema(implementation = TokenResponse.class)
									),
									@Content(
											mediaType = "application/xml",
											schema = @Schema(implementation = TokenResponse.class)
									)
							}
					),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<TokenResponse> login(AccountCredentials data);
	
	@Operation(
			summary = "Register new user",
			description = "Register new user in Book Store",
			responses = {
					@ApiResponse(
							description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	ResponseEntity<Void> register(RegisterCredentials data);
}

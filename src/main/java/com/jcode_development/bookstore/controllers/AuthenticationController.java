package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.user.User;
import com.jcode_development.bookstore.model.user.security.AccountCredentials;
import com.jcode_development.bookstore.model.user.security.RegisterCredentials;
import com.jcode_development.bookstore.model.user.security.TokenResponse;
import com.jcode_development.bookstore.repositories.UserRepository;
import com.jcode_development.bookstore.security.JwtTokenProvider;
import com.jcode_development.bookstore.swagger.AuthenticationDocumentation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController implements AuthenticationDocumentation {
	
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	
	public AuthenticationController(
			UserRepository userRepository,
			AuthenticationManager authenticationManager,
			JwtTokenProvider jwtTokenProvider
	) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<TokenResponse> login(@RequestBody AccountCredentials data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = jwtTokenProvider.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new TokenResponse(token));
	}
	
	@PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> register(@RequestBody RegisterCredentials data) {
		if (this.userRepository.findByUsername(data.username()) != null) {
			return ResponseEntity.badRequest().build();
		}
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User();
		newUser.setUserName(data.username());
		newUser.setPassword(encryptedPassword);
		newUser.setRole(data.role());
		
		this.userRepository.save(newUser);
		return ResponseEntity.ok().build();
	}
}

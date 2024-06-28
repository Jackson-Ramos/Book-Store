package com.jcode_development.bookstore.controllers;

import com.jcode_development.bookstore.model.user.User;
import com.jcode_development.bookstore.model.user.security.AccountCredentials;
import com.jcode_development.bookstore.model.user.security.RegisterCredentials;
import com.jcode_development.bookstore.model.user.security.TokenResponse;
import com.jcode_development.bookstore.repositories.UserRepository;
import com.jcode_development.bookstore.security.JwtTokenProvider;
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
public class AuthenticationController {
	
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	
	public AuthenticationController(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody AccountCredentials credentials) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = jwtTokenProvider.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new TokenResponse(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody RegisterCredentials credentials) {
		if (this.userRepository.findByUsername(credentials.username()) != null) {
			return ResponseEntity.badRequest().build();
		}
		String encryptedPassword = new BCryptPasswordEncoder().encode(credentials.password());
		User newUser = new User();
		newUser.setUserName(credentials.username());
		newUser.setPassword(encryptedPassword);
		newUser.setRole(credentials.role());
		
		this.userRepository.save(newUser);
		return ResponseEntity.ok().build();
	}
}

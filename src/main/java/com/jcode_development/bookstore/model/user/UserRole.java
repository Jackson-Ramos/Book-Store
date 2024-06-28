package com.jcode_development.bookstore.model.user;

import lombok.Getter;

@Getter
public enum UserRole {
	
	ADMIN("admin"),
	USER("user"),
	MANAGER("manager");
	
	private final String role;
	
	UserRole(String role) {
		this.role = role;
	}
	
}

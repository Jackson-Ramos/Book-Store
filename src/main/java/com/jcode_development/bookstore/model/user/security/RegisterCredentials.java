package com.jcode_development.bookstore.model.user.security;

import com.jcode_development.bookstore.model.user.UserRole;

public record RegisterCredentials (String username, String password, UserRole role){
}

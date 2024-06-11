package com.jcode_development.bookstore.model.book;

import java.util.Set;

public record BookRequest(
		String title,
		String review,
		String publisherId,
		Set<String> authorsIds) {
}

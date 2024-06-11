package com.jcode_development.bookstore.model.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcode_development.bookstore.model.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 8807087388084854682L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String comment;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne()
	@JoinColumn(name = "book_id")
	private Book book;
	
}
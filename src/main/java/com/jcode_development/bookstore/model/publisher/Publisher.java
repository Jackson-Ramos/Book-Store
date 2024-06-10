package com.jcode_development.bookstore.model.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcode_development.bookstore.model.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "publishers")
@AllArgsConstructor
@NoArgsConstructor
public class Publisher implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 8807087388084854682L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String name;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
	Set<Book> books = new HashSet<>();
}

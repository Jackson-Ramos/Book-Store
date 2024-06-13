package com.jcode_development.bookstore.model.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcode_development.bookstore.model.book.Book;
import com.jcode_development.bookstore.model.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
	private Set<Book> books = new HashSet<>();
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Publisher publisher = (Publisher) o;
		return Objects.equals(id, publisher.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}

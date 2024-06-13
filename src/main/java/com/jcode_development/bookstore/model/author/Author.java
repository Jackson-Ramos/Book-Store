package com.jcode_development.bookstore.model.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcode_development.bookstore.model.book.Book;
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
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -7313686895054977620L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
	private Set<Book> books = new HashSet<>();
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Author author = (Author) o;
		return Objects.equals(id, author.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}

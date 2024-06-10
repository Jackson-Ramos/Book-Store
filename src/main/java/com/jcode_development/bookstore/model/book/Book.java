package com.jcode_development.bookstore.model.book;

import com.jcode_development.bookstore.model.author.Author;
import com.jcode_development.bookstore.model.publisher.Publisher;
import com.jcode_development.bookstore.model.review.Review;
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
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 8862147290437218213L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String title;
	
	@OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
	private Review review;
	
	@ManyToOne()
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "books_authors",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id")
	)
	private Set<Author> authors = new HashSet<>();
	
}

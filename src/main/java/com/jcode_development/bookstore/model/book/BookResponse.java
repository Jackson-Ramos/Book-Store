package com.jcode_development.bookstore.model.book;

import com.jcode_development.bookstore.model.author.Author;
import com.jcode_development.bookstore.model.publisher.Publisher;
import com.jcode_development.bookstore.model.review.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse extends RepresentationModel<BookResponse> implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 3970418842140366764L;
	
	private String id;
	private String title;
	private Publisher publisher;
	private Set<Author> authors;
	private Review review;
}

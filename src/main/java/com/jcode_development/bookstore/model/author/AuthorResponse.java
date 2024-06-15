package com.jcode_development.bookstore.model.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorResponse extends RepresentationModel<AuthorResponse> implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -4909377030312432732L;
	
	private String id;
	private String name;
	
}

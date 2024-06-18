package com.jcode_development.bookstore.model.publisher;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherResponse extends RepresentationModel<PublisherResponse> implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -4523598429032727797L;
	
	private String id;
	private String name;
}

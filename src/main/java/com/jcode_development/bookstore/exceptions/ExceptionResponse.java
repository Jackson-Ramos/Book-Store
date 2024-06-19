package com.jcode_development.bookstore.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1721444075139007871L;
	
	private Date timestamp;
	private String message;
	private String details;
}

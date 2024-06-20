package com.jcode_development.bookstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Bookstore")
						.version("V1.0")
						.description("")
						.termsOfService("jcode_developer.com.br")
						.license(new License()
								.name("Apache 2.0")
								.url("jcode_developer.com.br")
						)
				);
	}
	
}

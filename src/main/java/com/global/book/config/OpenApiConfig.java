package com.global.book.config;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;

import org.apache.logging.log4j.core.config.builder.api.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {
	
	private static final String SECURITY_SCHEME_NAME="Bearer oAuth Token";
	
	@Bean
	public OpenAPI customOpenApi(@Value("${application-description}") String appDescription,
			@Value("${application-version}") String appVersion)
	{
		return new OpenAPI().info(new Info().title("sample Application Api")
				.version(appVersion)
				.contact(getContact())
				.description(appDescription)
				.termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apcahe 2.0").
						url("http://springdoc.org")))
				
				.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME,Arrays.asList("read","write")))
				.components(	
						new Components().addSecuritySchemes(SECURITY_SCHEME_NAME,
								new SecurityScheme().name(SECURITY_SCHEME_NAME)
								.scheme("bearer")
								.bearerFormat("JWT")));
	}	
	private io.swagger.v3.oas.models.info.Contact getContact()
	{
		Contact contact = new Contact();
		contact.setEmail("info@gmail.com");
		contact.setName("book service");
		contact.setUrl("https://www.book.com");
		contact.setExtensions(Collections.EMPTY_MAP);
		return contact;
		
	}
	
}

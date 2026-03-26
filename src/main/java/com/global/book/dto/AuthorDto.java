package com.global.book.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.global.book.base.BaseDto;
import com.global.book.entity.Book;
import com.global.book.validator.IpAddress;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto extends BaseDto<Long> {
	
	@NotBlank(message = "Should be enter author name")
	private String name; 
	
	@Email(message = "{validation.constraints.email.message}")
	private String email;
	
	private Long bookCount;
	private String imagePath;
}

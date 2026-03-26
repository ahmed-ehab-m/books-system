package com.global.book.entity;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.global.book.base.BaseEntity;
import com.global.book.validator.IpAddress;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Schema(name = "Book Entity")
@Entity
@Table(name = "authors")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@ToString  don't use  in entity
//@EqualsAndHashCode don't use in entity
//@Data // Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
// don't use data in entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author extends BaseEntity<Long>{
	
	
	@NotBlank(message = "Should be enter author name")
	private String name; 
	
//	@IpAddress(message = "validation.constraints.ip-address.message")
	@IpAddress
	private String ipAddress;
	
	@Email(message = "{validation.constraints.email.message}")
	private String email;
	
	
//	@NotEmpty
	@JsonManagedReference
	@OneToMany(mappedBy = "author")
	private List<Book> books = new ArrayList<>();
	
	@Formula("(select count(*) from books book where book.author_id = id)")
	private Long bookCount;
	
	private String imagePath;
	
}

package com.global.book.entity;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.global.book.base.BaseEntity;
import com.global.book.validator.IpAddress;

import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "authors")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author extends BaseEntity<Long>{
	
	@NotBlank(message = "Should be enter author name")
	private String name; 
	
	@IpAddress(message = "should be enter valid ip address")
	private String ipAddress;
	
	@Email
	private String email;
	
	
//	@NotEmpty
	@JsonManagedReference
	@OneToMany(mappedBy = "author")
	private List<Book> books = new ArrayList<>();
	
	@Formula("(select count(*) from books book where book.author_id = id)")
	private Long bookCount;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Long getBookCount() {
		return bookCount;
	}
	public void setBookCount(Long bookCount) {
		this.bookCount = bookCount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name; 
	}
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public void addBook(Book book)
	{
		books.add(book);
	}
	public void removeBook(Book book)
	{
		books.remove(book);
	}
	
	
	
	
}

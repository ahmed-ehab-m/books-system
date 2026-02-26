package com.global.book.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.global.book.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "authors")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author extends BaseEntity<Long>{
	
	private String name; 
	
//	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "author")
	private List<Book> books = new ArrayList<>();
	
	@Formula("(select count(*) from books book where book.author_id = id)")
	private Long bookCount;
	
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

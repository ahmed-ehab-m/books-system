package com.global.book.entity;



import java.time.LocalDateTime;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.global.book.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "books")
@NamedEntityGraph(name = "loadAuthor",attributeNodes =@NamedAttributeNode ("author"))

public class Book extends BaseEntity<Long>{
	

	private String name;
	private Double price;
	
	@Transient
	private Double discounted;
	
	
	@Formula("(select count(*) from books book) ")

	private Long bookCount;
	
	
	
	public Long getBookCount() {
		return bookCount;
	}

	public void setBookCount(Long bookCount) {
		this.bookCount = bookCount;
	}

	public Double getDiscounted() {
		return discounted;
	}

	public void setDiscounted(Double discounted) {
		this.discounted = discounted;
	}
	
	@PostLoad 
	private void calcDisCount()
	{
		this.setDiscounted(price*0.25);
	}

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	// foriegn key in the table , if i don't write  this statment sb will create it
	@JoinColumn(name = "author_id") 
	
	private Author author;

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id="+getId() +"name=" + name + ", price=" +
	price+" ]";
	}

}

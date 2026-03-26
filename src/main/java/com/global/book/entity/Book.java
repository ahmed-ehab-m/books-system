package com.global.book.entity;



import java.time.LocalDateTime;
import java.util.List;

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
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PostLoad;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@NamedEntityGraph(name = "loadAuthor",attributeNodes =@NamedAttributeNode ("author"))
@NamedStoredProcedureQuery(name = "Book.getBookByAuthor",procedureName = "GET_BOOK_BY_AUTHOR",
parameters = {
		@StoredProcedureParameter(mode=ParameterMode.IN,name = "author_id_in",type = String.class),
		@StoredProcedureParameter(mode=ParameterMode.OUT,name = "book_count",type= Integer.class)
})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity<Long>{
	
	@NotNull(message = "Should be enter book name")
	private String name;
	
	@Min(value=5)
	@Max(value=500)
	private Double price;
	
	@Transient
	private Double discounted;
	
	
	@Formula("(select count(*) from books book) ")

	private Long bookCount;
	
	@NotNull
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	// foriegn key in the table , if i don't write  this statment sb will create it
	@JoinColumn(name = "author_id") 
	
	private Author author;
	
	

}

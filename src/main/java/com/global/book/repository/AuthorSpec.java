package com.global.book.repository;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.entity.Book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AuthorSpec implements Specification<Author>{

	private AuthorSearch authorSearch;
	
	public AuthorSpec(AuthorSearch authorSearch) {
		super();
		this.authorSearch = authorSearch;
	}

	@Override
	public @Nullable Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		List<Predicate> predicates=new ArrayList<>();
		Join<Author,Book> bookJoin= root.join("books",JoinType.LEFT);
 		// author name
		if(authorSearch.getName() !=null && !authorSearch.getName().isEmpty())
		{
			predicates.add(cb.like(root.get("name"), authorSearch.getName())); 
		}
		
		// author email
		if(authorSearch.getEmail() !=null && !authorSearch.getEmail().isEmpty())
		{
			predicates.add(cb.like(root.get("email"), authorSearch.getEmail())); 
		}
		
		// author ipAddress
		if(authorSearch.getIpAddress() !=null && !authorSearch.getIpAddress().isEmpty())
		{
			predicates.add(cb.like(root.get("ipAddress"),"%"+ authorSearch.getIpAddress()+"%")); 
		}
		
		// author books
		if(authorSearch.getBookName() !=null && !authorSearch.getBookName().isEmpty())
		{
			predicates.add(cb.like(bookJoin.get("name" ), "%"+authorSearch.getBookName()+"%")); 
		}
		
		// author books
		if(authorSearch.getPrice() !=null)
		{
			predicates.add(cb.greaterThanOrEqualTo(bookJoin.get("price" ), authorSearch.getPrice())); 
		}		
		
		
		return cb.and(predicates.toArray(new Predicate[0]));
	}
	
}

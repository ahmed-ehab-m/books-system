package com.global.book.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.error.DuplicateRecordException;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.AuthorSpec;

@Service
public class AuthorService extends BaseService<Author, Long>{

	@Autowired
	private AuthorRepo authorRepo;
	
	Logger log=LoggerFactory.getLogger(AuthorService.class);
	
	@Override
	public Author insert(Author entity) {
		if(!entity.getEmail().isEmpty() && entity.getEmail() !=null)
		{
			Optional<Author> author=findByEmail(entity.getEmail());
			
			log.info("author name is : {}  and email is : {}",entity.getName(), entity.getEmail());
			System.out.println("email is : "+ entity.getEmail());
			if(author.isPresent())
				log.error("this email already found with another author");
				throw new DuplicateRecordException("this email already found with another author");
		}
		return super.insert(entity);
	}
	@Override
	public Author update(Author author)
	{
		Author entity=findById(author.getId());
		entity.setName(author.getName()); 	
		return super.update(entity);
	}
	
	public List<Author>findbyAuthorSpec(AuthorSearch authorSearch)
	{
		AuthorSpec spec=new AuthorSpec(authorSearch);
		return authorRepo.findAll(spec);
	}
	
	private Optional<Author> findByEmail(String email)
	{
		return authorRepo.findByEmail(email);
	}

}

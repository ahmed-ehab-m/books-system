package com.global.book.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.error.DuplicateRecordException;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.AuthorSpec;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service

//required not allargconstructor because all arg will include all variables even if not final 
// mean that will include Logger and this cause an error 
// required => include only final or notnull vars
@RequiredArgsConstructor // instead of autowired (constructor injection)

@Log4j2 // give me a log variable to use it in code

public class AuthorService extends BaseService<Author, Long>{

	private final  AuthorRepo authorRepo;
	
	
	@Override
	public Author insert(Author entity) {
		
		log.info(entity.getName());
		log.info(entity.getId());
		log.info(entity.getEmail());
		log.info(entity.getBookCount());
		log.info(entity.getImagePath());
		log.info(entity.getIpAddress());
		
		if( entity.getEmail() !=null && !entity.getEmail().isEmpty())
		{
			
			Optional<Author> existingAuthor = findByEmail(entity.getEmail());			
			log.info("author name is : {}  and email is : {}",entity.getName(), entity.getEmail());
			System.out.println("email is : "+ entity.getEmail());
			if(existingAuthor.isPresent())
			{
				log.error("this email already found with another author");
				throw new DuplicateRecordException("this email already found with another author");
			}
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
	
	@Async(value="threadPoolTaskExecutor")
	public Optional<Author> findByEmail(String email) {
	    return authorRepo.findByEmail(email);
	}

}

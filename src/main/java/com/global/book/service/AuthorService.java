package com.global.book.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.entity.Author;
import com.global.book.repository.AuthorRepo;

@Service
public class AuthorService {
	private AuthorRepo authorRepo;

	public AuthorService(AuthorRepo authorRepo) {
		super();
		this.authorRepo = authorRepo; 
	}
	
	public Author findById(Long id)
	{
		return authorRepo.findById(id).orElseThrow();
	}
	public Author getReferenceById(Long id)
	{
		return authorRepo.getReferenceById(id);
	}
	
	public List<Author> findAll()
	{
		return authorRepo.findAll();
	}
	
	public Author insert(Author author)
	{
		if(author.getId() !=null)
		{
			throw new RuntimeException();
		}
		return authorRepo.save(author);
	}
	
	public List<Author> insertAll(List<Author> authors)
	{
			 
		return authorRepo.saveAll(authors);
	}
	
	public Author update(Author author)
	{
		Author entity=findById(author.getId());
		entity.setName(author.getName());
		return authorRepo.save(entity);
	}
	
	public void deleteById(Long id)
	{
		 authorRepo.deleteById(id);
	}
}

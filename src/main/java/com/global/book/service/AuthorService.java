package com.global.book.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.AuthorSpec;

@Service
public class AuthorService extends BaseService<Author, Long>{

	@Autowired
	private AuthorRepo authorRepo;
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

}

package com.global.book.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.repository.AuthorRepo;

@Service
public class AuthorService extends BaseService<Author, Long>{

	@Override
	public Author update(Author author)
	{
		Author entity=findById(author.getId());
		entity.setName(author.getName()); 	
		return super.update(entity);
	}
	

}

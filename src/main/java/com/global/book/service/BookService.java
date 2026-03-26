package com.global.book.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.BookRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor // instead of autowired (constructor injection)

@Log4j2 // give me a log variable to use it in code
public class BookService extends BaseService<Book,Long> {
	private final BookRepo bookRepo;
	private final static String USERS_PROC=".INSERT_JP_USERS";
//	
//	@Autowired
//	private Environment env;

//	public BookService(BookRepo bookRepo) {
//		super();
//		this.bookRepo = bookRepo; 
//	}
	public List<Book> insertALL(List<Book>  books)
	{
		
		return bookRepo.saveAll(books);
	}
	public Book update(Book book)
	{
		Book entity=findById(book.getId());
		entity.setName(book.getName());
		return super.update(entity);
	}
	public int deleteByAuthorId(Long id)
	{
		bookRepo.deleteAllInBatch();
		return bookRepo.deleteByAuthorId(id);
	}
	
}

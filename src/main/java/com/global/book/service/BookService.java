package com.global.book.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.BookRepo;

@Service
public class BookService extends BaseService<Book,Long> {
	private BookRepo bookRepo;

	public BookService(BookRepo bookRepo) {
		super();
		this.bookRepo = bookRepo; 
	}
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

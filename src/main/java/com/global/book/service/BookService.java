package com.global.book.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.BookRepo;

@Service
public class BookService {
	private BookRepo bookRepo;

	public BookService(BookRepo bookRepo) {
		super();
		this.bookRepo = bookRepo; 
	}
	
	public Book findById(Long id)
	{
		return bookRepo.findById(id).orElseThrow();
	}
	
	public List<Book> findAll()
	{
		return bookRepo.findAll();
	}
	
	public Book insert(Book book)
	{
		if(book.getId() !=null)
		{
			throw new RuntimeException();
		}
		return bookRepo.save(book);
	}
	
	public List<Book> insertALL(List<Book>  books)
	{
		
		return bookRepo.saveAll(books);
	}
	
	public Book update(Book book)
	{
		Book entity=findById(book.getId());
		entity.setName(book.getName());
		return bookRepo.save(entity);
	}
	
	public void deleteById(Long id)
	{
		bookRepo.deleteById(id);
	}
}

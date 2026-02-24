package com.global.book.config;

import java.util.Arrays;

import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.service.AuthorService;
import com.global.book.service.BookService;

@Component
public class StartUpApp implements CommandLineRunner{
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Author author1=new Author();
		author1.setName("ahmed");
	
			
		Author author2=new Author();
		author2.setName("mohamed");
		
		Author author3=new Author();
		author3.setName("youssef");
		
		authorService.insertAll(Arrays.asList(author1,author2,author3));	 
		
		Book book1=new Book();
		book1.setName("Java");
		book1.setPrice(500.0);
		book1.setAuthor(authorService.findById(1L));
			
		Book book2=new Book();
		book2.setName("c++");
		book2.setPrice(300.0);
		book2.setAuthor(authorService.findById(2L));
		
		
		Book book3=new Book();
		book3.setName("python");
		book3.setPrice(200.0);
		book3.setAuthor(authorService.findById(3L));
		
		bookService.insertALL(Arrays.asList(book1,book2,book3));	 
	}

}

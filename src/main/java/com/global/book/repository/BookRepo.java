package com.global.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.book.entity.Author;
import com.global.book.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>{
	@Override
	@EntityGraph(value = "loadAuthor") 
	Optional<Book> findById(Long id);
	
	@Override
	@EntityGraph(value = "loadAuthor") 
	List<Book> findAll();
}

package com.global.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.book.base.BaseRepo;
import com.global.book.entity.Author;
import com.global.book.entity.Book;

@Repository
public interface BookRepo extends BaseRepo<Book, Long>{
	@Override
	@EntityGraph(value = "loadAuthor") 
	Optional<Book> findById(Long id);
	
	@Override
	@EntityGraph(value = "loadAuthor") 
	List<Book> findAll();
	
	@Transactional
	@Modifying
	@Query(value = "delete from Book where author.id= :id")
	int deleteByAuthorId(Long id);
	
//	@Procedure
//	int GET_BOOK_BY_AUTHOR(String author_id_in);
//	 int getBookByAuthor(String author_id_in);
	@Query(value = "CALL GET_BOOK_BY_AUTHOR(:author_id_in);",nativeQuery = true)
	int getBookByAuthor(@Param("author_id_in") String author_id_in);
}

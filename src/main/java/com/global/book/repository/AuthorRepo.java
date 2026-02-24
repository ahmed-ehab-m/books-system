package com.global.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.book.entity.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long>{
	
}

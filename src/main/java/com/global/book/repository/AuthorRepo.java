package com.global.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.global.book.base.BaseRepo;
import com.global.book.entity.Author;

@Repository
public interface AuthorRepo extends BaseRepo<Author,Long> ,JpaSpecificationExecutor<Author> {
	
}

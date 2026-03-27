package com.global.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.global.book.dto.AuthorDto;
import com.global.book.dto.BookDto;
import com.global.book.entity.Author;
import com.global.book.entity.Book;

@Mapper(uses= {AuthorMapper.class})
public interface BookMapper {
	
	@Mapping(target = "author" ,ignore = true)
	@Mapping(target = "authorName",source = "author.name") // mapping only fields from the big entity
	@Mapping(target = "authorEmail",source = "author.email") // mapping only fields from the big entity
	BookDto map(Book entity);
	
	@Mapping(source = "authorName",target = "author.name") // mapping only fields from the big entity
	@Mapping(source = "authorEmail",target = "author.email") // mapping only fields from the big entity
	Book unMap(BookDto dto);
	
}

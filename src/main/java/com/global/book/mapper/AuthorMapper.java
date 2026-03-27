package com.global.book.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.i18n.LocaleContextHolder;

import com.global.book.dto.AuthorDto;
import com.global.book.entity.Author;

@Mapper(imports = {LocaleContextHolder.class})
public interface AuthorMapper {	 
	
	@Mapping(target = "bookCount",ignore = true)
	@Mapping(target = "name",defaultExpression = "java(LocaleContextHolder.getLocale()."
			+ "getLanguage()== \"ar\" ? entity.getName() : entity.getName()) ")
	AuthorDto map(Author entity);
	
	Author unMap(AuthorDto dto);
	
	@AfterMapping // execute 
	default void mapName(Author entity ,@MappingTarget AuthorDto authorDto)
	{
		if(entity.getName() !=null)
		{
			String lang =LocaleContextHolder.getLocale().getLanguage();
			authorDto.setName(lang.equals("ar") ? entity.getName() : entity.getName());
		}
	}
	
}

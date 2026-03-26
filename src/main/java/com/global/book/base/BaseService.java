package com.global.book.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.global.book.error.RecordNotFoundException;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass

public class BaseService <T extends BaseEntity<ID> ,ID extends Number>{
	
	@Autowired
	private BaseRepo<T, ID> baseRepo;
	
	@Autowired
	private MessageSource messageSource;
	
	public T findById(ID id)
	{
		Optional<T> entity= baseRepo.findById(id);
		if(entity.isPresent())
		{
			return entity.get();
		}
		else
		{
			String [] msgParam= {id.toString()};
			String message = messageSource.getMessage("validation.recordNotFound.message", msgParam , LocaleContextHolder.getLocale());
		throw new RecordNotFoundException(message);
		}
	}
	public T getReferenceById(ID id)
	{
		return baseRepo.getReferenceById(id);
	}
	
	public List<T> findAll()
	{
		return baseRepo.findAll();
	}
	
	public T insert(T entity)
	{
		if(entity.getId() !=null)
		{
			throw new RuntimeException();
		}
		return baseRepo.save(entity);
	}
	
	public List<T> insertAll(List<T> entitys)
	{
			 
		return baseRepo.saveAll(entitys);
	}
	
	public T update(T entity)
	{
		return baseRepo.save(entity);
	}
	
	public void deleteById(ID id)
	{
		baseRepo.deleteById(id);
	}
}

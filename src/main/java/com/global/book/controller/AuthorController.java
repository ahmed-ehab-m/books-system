package com.global.book.controller;


import java.util.concurrent.Executor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.AmazonS3;
import com.global.book.DataJpaBooksProjectApplication;
import com.global.book.dto.AuthorDto;
import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.mapper.AuthorMapper;
import com.global.book.service.AuthorService;
import com.global.book.service.FileUploadService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Validated
@RestController
@RequestMapping("/author")
@RequiredArgsConstructor // instead of autowired (constructor injection)

public class AuthorController {

	private final AuthorService authorService;
	private final AuthorMapper authorMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable  Long id)
	{
		Author author= authorService.findById(id);
		AuthorDto authorDto =authorMapper.map(author);
		return ResponseEntity.ok(authorDto);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email)
	{
		Author author= authorService.findByEmail(email).get();
		AuthorDto authorDto =authorMapper.map(author);

		return ResponseEntity.ok(authorDto);
	}
	
	@GetMapping("")
	public ResponseEntity<?> findAll()
	{
		return ResponseEntity.ok(authorService.findAll());
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@Valid @RequestBody AuthorDto authorDto)
	{		
//		authorEntity.setName(authorDto.getName());
		Author authorEntity =authorMapper.unMap(authorDto);
		Author entity=authorService.insert(authorEntity);
		AuthorDto returnDto=authorMapper.map(entity);
		return ResponseEntity.ok(returnDto);
		
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@Valid  @RequestBody AuthorDto authorDto)
	{
		Author authorEntity =authorMapper.unMap(authorDto);
		Author entity=authorService.update(authorEntity);
		AuthorDto returnDto=authorMapper.map(entity);
		return ResponseEntity.ok(returnDto);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id)
	{
	 authorService.deleteById(id);
	 return ResponseEntity.ok(null); 
	 }
	
	@PostMapping("/spec")
	public ResponseEntity<?> findByAuthorSpec(@RequestBody  AuthorSearch authorSearch)
	{
		return ResponseEntity.ok(authorService.findbyAuthorSpec(authorSearch));
	}
	
}

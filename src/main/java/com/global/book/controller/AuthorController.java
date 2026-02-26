package com.global.book.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.Author;
import com.global.book.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {
	private AuthorService authorService;
	public AuthorController(AuthorService authorService) {
		super();
		this.authorService = authorService;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
	{
		return ResponseEntity.ok( authorService.findById(id));
	}
	
	@GetMapping("")
	public ResponseEntity<?> findAll()
	{
		return ResponseEntity.ok(authorService.findAll());
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@Valid @RequestBody Author author)
	{
		
		return ResponseEntity.ok(authorService.insert(author));
		
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@Valid  @RequestBody Author author)
	{
		return ResponseEntity.ok(authorService.update(author));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id)
	{
	 authorService.deleteById(id);
	 return ResponseEntity.ok(null);
	 }
}

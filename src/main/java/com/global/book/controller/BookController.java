package com.global.book.controller;


import java.util.concurrent.Executor;

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

import com.amazonaws.services.s3.AmazonS3;
import com.global.book.dto.BookDto;
import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.service.AuthorService;
import com.global.book.service.BookService;
import com.global.book.service.FileUploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/book")
@Tag(name = "book service api controller")
@RequiredArgsConstructor // instead of autowired (constructor injection)

public class BookController {
	private final BookService bookService;

	
	
	@Operation(summary = "Get book by it's id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found The Book",
					content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = Book.class))}),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied",
			content = {@Content}),
			@ApiResponse(responseCode = "404", description = "book not found",
			content = {@Content}),
			
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@Parameter(example = "20",name = "Book Id" )@PathVariable Long id)
	{
		Book book=bookService.findById(id);
		 BookDto dto=new BookDto();
		 dto.setId(book.getId()); 
		 dto.setName(book.getName());
		 dto.setPrice(book.getPrice());
//		 dto.setAuthor(book.getAuthor());
		return ResponseEntity.ok(dto);
	}
	
	@Operation(summary = "Get All Books")
	@GetMapping("")
	public ResponseEntity<?> findAll()
	{
		return ResponseEntity.ok(bookService.findAll());
	}
	
	@Operation(summary = "insert book")
	@PostMapping("")
	public ResponseEntity<?> insert(@Valid @RequestBody BookDto dto)
	{
		Book book=new Book();
		book.setName(dto.getName());
		book.setPrice(dto.getPrice());
//		book.setAuthor(dto.getAuthor());
		return ResponseEntity.ok(bookService.insert(book));
		
	}
	
	@Operation(summary = "update book")
	@PutMapping("")
	public ResponseEntity<?> update(@Valid @RequestParam Book book)
	{
		return ResponseEntity.ok( bookService.update(book));
	}
	
	@Operation(summary = "delete book by it's id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id)
	{
		bookService.deleteById(id);
	 return ResponseEntity.ok(null);
	 }
	
	@Operation(summary = "delete book by it's Author id")
	@DeleteMapping("/author/{id}")
	public ResponseEntity<?> deleteByAuthorId(@PathVariable Long id)
	{
		 bookService.deleteByAuthorId(id);
		 return ResponseEntity.ok(null);
	}
}

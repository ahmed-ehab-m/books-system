package com.global.book.controller;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.AmazonS3;
import com.global.book.dto.ProductDto;
import com.global.book.service.AuthorService;
import com.global.book.service.FileUploadService;
import com.global.book.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor // instead of autowired (constructor injection)

public class ProductsController {
	private final ProductService productService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id)
	{
		return ResponseEntity.ok( productService.getProductById(id));
	} 
	
	@GetMapping("")
	public ResponseEntity<?> getAllProducts()
	{
		return ResponseEntity.ok( productService.getAllProducts());
	} 
	
	
	@PostMapping("")
	public ResponseEntity<?> addProduct(@RequestBody ProductDto product)
	{
		return ResponseEntity.ok( productService.addProduct(product));
	} 
}

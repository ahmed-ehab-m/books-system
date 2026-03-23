package com.global.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.ProductDto;
import com.global.book.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	private ProductService productService;
	
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

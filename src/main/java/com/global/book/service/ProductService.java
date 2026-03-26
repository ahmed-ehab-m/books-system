package com.global.book.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.book.dto.ProductDto;
import com.global.book.repository.AuthorRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service

public class ProductService {
	
	private static String BASE_PRODUCT_URL="https://dummyjson.com/products";
	
	//for test only 
	private  RestTemplate restTemplate =new RestTemplate();
	public ProductDto getProductById(Long id)
	{
		 // get for object => will return the body only
		 // get for entity => to return response entity
		 // response entity will return body , headers , status code
		ResponseEntity<ProductDto> result=  restTemplate.getForEntity(BASE_PRODUCT_URL+"/"+id, ProductDto.class );
		return result.getBody();
	}
	//////////////////////////
	public List<ProductDto> getAllProducts()
	{
		 // get for entity => to return response entity
		 // response entity will return body , headers , status code
		ResponseEntity<List> result=  restTemplate.getForEntity(BASE_PRODUCT_URL, List.class );
		return result.getBody();
	}
	
	//////////////////////////
	public ProductDto addProduct(ProductDto product)
	{
	
	// http entity => to send an data to another server
	// you should add it into package called http entity
	// http entity is class in java holds two things
	// first => body => the actual object
	// second => headers => data about object and another information to the second server 
	// to understand how to work with this object or this request
	// if i don't add headers => SB will add default headers
	
	HttpHeaders headers=new HttpHeaders();
	headers.add("accept", "application/json");
	headers.add("accept-language", "en");
	
	HttpEntity<ProductDto> request =new HttpEntity<>(product ,headers);
	
	ResponseEntity<ProductDto > result=  restTemplate.postForEntity(BASE_PRODUCT_URL,request,ProductDto.class );
	return result.getBody();
	}
	
	//////////////////////////
	public void updateProduct(ProductDto product)
	{
	
	HttpEntity<ProductDto> request =new HttpEntity<>(product);
	
	 restTemplate.put(BASE_PRODUCT_URL,request,ProductDto.class );
	}
	/////////////////////////
	public void deleteProductById(Long id)
	{
		 restTemplate.delete(BASE_PRODUCT_URL+"/"+id );
	}
}

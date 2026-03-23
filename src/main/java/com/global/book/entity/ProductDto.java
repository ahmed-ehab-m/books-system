package com.global.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// talk to jackson that any additional data skip it
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
	
	
	private Long id;
    private String title;
    private Double price;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}

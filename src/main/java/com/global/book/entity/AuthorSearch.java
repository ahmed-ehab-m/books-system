package com.global.book.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorSearch {
	
	 
	private String name;
	private String email;
	private String ipAddress;
	
	private String bookName;
	
	private Double price;
	
	
	
}

package com.global.book.dto;


import java.util.List;

import com.global.book.base.BaseDto;
import com.global.book.entity.Author;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends BaseDto<Long> {
	
	@NotBlank
	private String name;
	@Min(value=5)
	@Max(value=500)
	private Double price;	
	
	@NotNull
	private AuthorDto author;
		
	private String authorName;
	
	private String authorEmail;
	
}

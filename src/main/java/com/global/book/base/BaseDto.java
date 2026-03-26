package com.global.book.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseDto<ID> {
	
	private ID id;
	private String statusCode;
	private Boolean isDeleted;
}

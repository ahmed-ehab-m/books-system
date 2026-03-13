package com.global.book.error;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
	private String message;
	private Boolean success;
	private List<String> details;
	private LocalDateTime dateTime;
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
		this.success=Boolean.FALSE;
		this.dateTime=LocalDateTime.now();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}

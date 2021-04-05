package com.cm.concurrencymultithreading.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiError {
	private String message;
	private HttpStatus status;
	private List<String> errors;

	public ApiError(HttpStatus status, String message, List<String> errors) {
		super();
		this.message = message;
		this.status = status;
		this.errors = errors;
	}

	public ApiError(HttpStatus status, String message, String errors) {
		super();
		this.message = message;
		this.status = status;
		this.errors = Arrays.asList(errors);
	}

}

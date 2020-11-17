package com.demo.repository.entity;

import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class ExceptionResponse {
	private Boolean success = false;
	private String message;

	public ExceptionResponse(String message) {
		this.message = message;
	}
}

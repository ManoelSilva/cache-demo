package com.demo.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.repository.entity.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
	private static final String EXCEPTION_WITHOUT_MESSAGE = "Handler error with ExceptionHandlerAdvice: ";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
		return this.executeExceptionHandler(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
		return this.executeExceptionHandlerWithoutMessage(ex, HttpStatus.NO_CONTENT);
	}

	private ResponseEntity<ExceptionResponse> executeExceptionHandler(Throwable ex, HttpStatus status) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), status);
	}

	private ResponseEntity<ExceptionResponse> executeExceptionHandlerWithoutMessage(Throwable ex, HttpStatus status) {
		log.error(EXCEPTION_WITHOUT_MESSAGE, ex);
		return new ResponseEntity<>(new ExceptionResponse(), status);
	}
}

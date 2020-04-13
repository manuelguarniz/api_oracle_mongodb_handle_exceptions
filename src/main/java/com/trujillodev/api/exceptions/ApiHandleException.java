package com.trujillodev.api.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.trujillodev.api.models.response.ApiError;
import com.trujillodev.api.utils.Utils;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiHandleException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ApiError error = ApiError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.exception(ex.getClass().getName())
				.message(Utils.getValueOrDefault(ex.getMessage(), "Mensaje no disponible"))
				.build();
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@ExceptionHandler(ValidationException.class)
//	public final ResponseEntity<Object> handleValidationRequestException(ValidationException ex, WebRequest request) {
//		List<String> details = new ArrayList<String>();
//		details.add(ex.getLocalizedMessage());
//		ApiError error = ApiError.builder()
//				.timestamp(LocalDateTime.now())
//				.status(HttpStatus.BAD_REQUEST.value())
//				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
//				.exception(ex.getClass().getName())
//				.message(ex.getMessage())
//				.details(details)
//				.build();
//		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
//	}

	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errors.add(error.getDefaultMessage());
		}
		ApiError error = ApiError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.errors(errors)
				.build();
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError error = ApiError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.exception(ex.getClass().getName())
				.message(Utils.getValueOrDefault(ex.getMessage(), "Mensaje no disponible"))
				.build();
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

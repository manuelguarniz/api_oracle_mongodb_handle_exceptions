package com.trujillodev.api.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationRequestException extends RuntimeException {

	public ValidationRequestException(String exception) {
		super(exception);
	}
}

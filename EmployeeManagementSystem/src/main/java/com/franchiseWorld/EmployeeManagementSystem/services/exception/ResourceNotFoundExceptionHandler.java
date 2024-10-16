package com.franchiseWorld.EmployeeManagementSystem.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResourceException> handleResourceNotFoundException(ResourceNotFoundException exception) {
		ResourceException resourceException = new ResourceException(exception.getMessage(), exception.getCause(),
				HttpStatus.NOT_FOUND);

		return new ResponseEntity<ResourceException>(resourceException, HttpStatus.NOT_FOUND);

	}

}

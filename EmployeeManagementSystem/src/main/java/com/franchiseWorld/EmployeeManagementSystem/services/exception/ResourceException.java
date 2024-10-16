package com.franchiseWorld.EmployeeManagementSystem.services.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceException {
	
	private final String message;
	private final Throwable throwable; 
	private final HttpStatus httpStatus;

}

package com.franchiseWorld.EmployeeManagementSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.franchiseWorld.EmployeeManagementSystem.Response.ResponseStructure;


@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	

	@ExceptionHandler(NoUserPresentException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(NoUserPresentException ex) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("User Not Found");
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
}

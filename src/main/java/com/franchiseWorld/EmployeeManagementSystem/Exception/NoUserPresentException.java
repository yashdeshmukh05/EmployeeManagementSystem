package com.franchiseWorld.EmployeeManagementSystem.Exception;

import lombok.Data;

@Data
public class NoUserPresentException extends RuntimeException {

	private String message;
	
	public NoUserPresentException(String msg){
		message = msg;
	}
	
}

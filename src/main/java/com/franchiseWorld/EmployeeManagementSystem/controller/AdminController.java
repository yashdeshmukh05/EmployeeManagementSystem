package com.franchiseWorld.EmployeeManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.franchiseWorld.EmployeeManagementSystem.Entity.User;
import com.franchiseWorld.EmployeeManagementSystem.Response.ResponseStructure;
import com.franchiseWorld.EmployeeManagementSystem.Service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService service;
	
	
	@PostMapping("/save")
	 public ResponseEntity<ResponseStructure<User>> addEmp(@RequestBody User user) {
		  return service.addEmp(user);
	 }
	@DeleteMapping("/remove/{userId}")
	public <T> ResponseEntity<ResponseStructure<T>> deleteEmp(@PathVariable Long userId) {
		return service.deleteEmp(userId);
	}
	
}

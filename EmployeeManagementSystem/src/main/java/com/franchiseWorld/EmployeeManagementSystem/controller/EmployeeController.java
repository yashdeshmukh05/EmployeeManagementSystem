package com.franchiseWorld.EmployeeManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.franchiseWorld.EmployeeManagementSystem.Entity.User;

import com.franchiseWorld.EmployeeManagementSystem.services.EmployeeService;



@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	//  view self profile
	@GetMapping("/profile/{userId}")
	public ResponseEntity<User> viewSelfProfile(@PathVariable Long userId) {
		User userProfile = employeeService.viewSelfProfile(userId);
		return new ResponseEntity<User>(userProfile, HttpStatus.OK);
		
	}

	//  update task progress
	@PutMapping("/tasks/{taskId}/progress")
	public ResponseEntity<String> updateTaskProgress(@PathVariable Long taskId, @RequestParam String newProgress) {
		employeeService.updateTaskProgress(taskId, newProgress);
		return ResponseEntity.ok("Task progress updated successfully.");
	}
}

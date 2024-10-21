package com.franchiseWorld.EmployeeManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;
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

	@DeleteMapping("/remove/{empId}")
	public <T> ResponseEntity<ResponseStructure<T>> deleteEmp(@PathVariable Long empId) {
		return service.deleteEmp(empId);
	}

	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseStructure<User>> updateEmp(@RequestBody User user, @PathVariable Long empId) {
		return service.updateEmp(user, empId);
	}

	@GetMapping("/view/Employees")
	public ResponseEntity<ResponseStructure<List<User>>> viewEmps() {
		return service.viewEmployees();
	}
	
	@GetMapping("/view/{empId}")
	public ResponseEntity<ResponseStructure<User>> viewEmpById(@PathVariable Long empId){
		return service.viewEmpById(empId);
	}
	
	@PostMapping("/assign/HR/{empId}")
	public ResponseEntity<ResponseStructure<User>> assignHrRole(@PathVariable Long empId) {
		return service.assignHrRole(empId);
	}
	
	@GetMapping("/view/task/{empId}")
	public ResponseEntity<ResponseStructure<List<Task>>> seeEmpTaskProgress(@PathVariable Long empId) {
		return service.viewEmpTask(empId);
	}
	
	
	

}

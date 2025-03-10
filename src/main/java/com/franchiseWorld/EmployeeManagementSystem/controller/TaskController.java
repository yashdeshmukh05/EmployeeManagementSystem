package com.franchiseWorld.EmployeeManagementSystem.controller;

import java.util.List;

import com.franchiseWorld.EmployeeManagementSystem.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;


@RestController
@RequestMapping("api/employee/tasks")
@CrossOrigin
public class TaskController {

	@Autowired
	private TaskService taskService;

	// get tasks for a specific user
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long userId) {
		List<Task> tasks = taskService.getUserTasks(userId);
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	// get a specific task by task ID
	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
		Task task = taskService.getTaskById(taskId);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

//	  a new task and assign it to a usercreate
	@PostMapping("/user/{userId}")
	public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody Task task) {
		Task createdTask = taskService.createTask(task, userId);
		return new ResponseEntity<Task>(createdTask, HttpStatus.CREATED);
	}

	// update an existing task
	@PutMapping("/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task taskDetails) {
		Task updatedTask = taskService.updateTask(taskId, taskDetails);
		return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
	}

	// delete a task by task ID
	@DeleteMapping("/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.ok("Task deleted successfully.");
	}
}

package com.franchiseWorld.EmployeeManagementSystem.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;
import com.franchiseWorld.EmployeeManagementSystem.Entity.User;


import com.franchiseWorld.EmployeeManagementSystem.repository.TaskRepository;
import com.franchiseWorld.EmployeeManagementSystem.repository.UserRepository;
import com.franchiseWorld.EmployeeManagementSystem.services.exception.ResourceNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	// Method to view employee's own profile
	public User viewSelfProfile(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

		List<Task> tasks = taskRepository.findByUserId(userId);
		user.setTasks(tasks); // Set tasks for the user

		return user; // Returning the full User entity
	}

	// Method to update task progress
	public void updateTaskProgress(Long taskId, String newProgress) {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

		task.setProgress(newProgress);
		taskRepository.save(task); // Save updated task
	}
}
package com.franchiseWorld.EmployeeManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import com.franchiseWorld.EmployeeManagementSystem.Exception.ResourceNotFoundException;
import com.franchiseWorld.EmployeeManagementSystem.Repository.TaskRepository;
import com.franchiseWorld.EmployeeManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;
import com.franchiseWorld.EmployeeManagementSystem.Entity.User;



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
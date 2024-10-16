package com.franchiseWorld.EmployeeManagementSystem.Service;

import java.util.List;

import com.franchiseWorld.EmployeeManagementSystem.Exception.ResourceNotFoundException;
import com.franchiseWorld.EmployeeManagementSystem.Repository.TaskRepository;
import com.franchiseWorld.EmployeeManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;
import com.franchiseWorld.EmployeeManagementSystem.Entity.User;


@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	// Get tasks for a specific user
	public List<Task> getUserTasks(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

		return taskRepository.findByUserId(userId);
	}

	// Get a specific task by task ID
	public Task getTaskById(Long taskId) {
		return taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));
	}

	// Create a new task
	public Task createTask(Task task, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
		task.setUser(user);
		return taskRepository.save(task);
	}

	// Update an existing task
	public Task updateTask(Long taskId, Task taskDetails) {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

		task.setName(taskDetails.getName());
		task.setDescription(taskDetails.getDescription());
		task.setDueDate(taskDetails.getDueDate());
		task.setProgress(taskDetails.getProgress());

		return taskRepository.save(task); // Save updated task
	}

	// Delete a task by task ID
	public void deleteTask(Long taskId) {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + taskId));

		taskRepository.delete(task); // Remove task from the database
	}
}

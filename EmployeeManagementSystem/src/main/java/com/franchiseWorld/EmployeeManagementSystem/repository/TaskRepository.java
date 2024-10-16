package com.franchiseWorld.EmployeeManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;



public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByUserId(Long userId);
	
}

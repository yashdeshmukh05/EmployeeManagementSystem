package com.franchiseWorld.EmployeeManagementSystem.Entity;

import java.time.LocalDate;

import com.franchiseWorld.EmployeeManagementSystem.Entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String progress;

	@Column(name = "due_date")
	private LocalDate dueDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
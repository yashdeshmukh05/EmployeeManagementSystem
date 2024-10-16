package com.franchiseWorld.EmployeeManagementSystem.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.franchiseWorld.EmployeeManagementSystem.Entity.User;
import com.franchiseWorld.EmployeeManagementSystem.Repository.UserRepository;

@Repository
public class AdminDao {

	@Autowired
	private UserRepository repository;

	public User save(User user) {
		return repository.save(user);
	}

	public Optional<User> findEmpById(Long id) {
		return repository.findById(id);

	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}

package com.franchiseWorld.EmployeeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franchiseWorld.EmployeeManagementSystem.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
   
}

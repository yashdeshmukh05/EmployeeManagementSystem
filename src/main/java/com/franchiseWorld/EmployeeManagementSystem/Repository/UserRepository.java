package com.franchiseWorld.EmployeeManagementSystem.Repository;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;
import com.franchiseWorld.EmployeeManagementSystem.Entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
    
}
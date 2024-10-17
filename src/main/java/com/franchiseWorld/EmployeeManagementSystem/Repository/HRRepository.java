package com.franchiseWorld.EmployeeManagementSystem.Repository;

import com.franchiseWorld.EmployeeManagementSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRRepository extends JpaRepository<User,Long> {

    User findByIdOrFirstName(Long id, String firstName);
}

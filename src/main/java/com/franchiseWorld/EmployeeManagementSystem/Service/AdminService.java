package com.franchiseWorld.EmployeeManagementSystem.Service;

import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.franchiseWorld.EmployeeManagementSystem.Dao.AdminDao;
import com.franchiseWorld.EmployeeManagementSystem.Entity.User;
import com.franchiseWorld.EmployeeManagementSystem.Entity.UserRole;
import com.franchiseWorld.EmployeeManagementSystem.Exception.NoUserPresentException;
import com.franchiseWorld.EmployeeManagementSystem.Response.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;

	public ResponseEntity<ResponseStructure<User>> addEmp(User user) {

		user.setRole(UserRole.ROLE_EMPLOYEE);
		User user2 = dao.save(user);
		ResponseStructure<User> structure = new ResponseStructure<User>();
		if (user2 != null) {
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Employee Added");
			structure.setData(user2);
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
		}
		structure.setStatus(HttpStatus.FAILED_DEPENDENCY.value());
		structure.setMessage("Failed to save the employee");
		structure.setData(null);
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FAILED_DEPENDENCY);

	}

	public <T> ResponseEntity<ResponseStructure<T>> deleteEmp(Long id) {

		ResponseStructure<T> structure = new ResponseStructure<>();
		if (dao.findEmpById(id).isPresent()) {
			dao.delete(id);
			structure.setStatus(HttpStatus.NO_CONTENT.value());
			structure.setMessage("Employee Deleted");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<T>>(structure, HttpStatus.NO_CONTENT);

		}
		else {
			throw new NoUserPresentException("No Employee Present With the given Id : "+id);
		}

	}

}

package com.franchiseWorld.EmployeeManagementSystem.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;
import com.franchiseWorld.EmployeeManagementSystem.Entity.User;
import com.franchiseWorld.EmployeeManagementSystem.Entity.UserRole;
import com.franchiseWorld.EmployeeManagementSystem.Exception.NoUserPresentException;
import com.franchiseWorld.EmployeeManagementSystem.Exception.ResourceNotFoundException;
import com.franchiseWorld.EmployeeManagementSystem.Repository.TaskRepository;
import com.franchiseWorld.EmployeeManagementSystem.Repository.UserRepository;
import com.franchiseWorld.EmployeeManagementSystem.Response.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TaskRepository taskRepo;

	public ResponseEntity<ResponseStructure<User>> addEmp(User user) {

		user.setRole(UserRole.ROLE_EMPLOYEE);
		User user2 = repo.save(user);
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

		if (repo.findById(id).isPresent()) {
			ResponseStructure<T> structure = new ResponseStructure<>();
			repo.deleteById(id);
			structure.setStatus(HttpStatus.NO_CONTENT.value());
			structure.setMessage("Employee Deleted");
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<T>>(structure, HttpStatus.NO_CONTENT);

		} else {
			throw new NoUserPresentException("No Employee Present With the given Id : " + id);
		}

	}

	public ResponseEntity<ResponseStructure<User>> updateEmp(User user, Long empId) {
		User user2 = repo.findById(empId).get();
		if (user2 != null) {
			user.setId(empId);

			if (user.getFirstName() == null) {
				user.setFirstName(user2.getFirstName());
			}
			if (user.getLastName() == null) {
				user.setLastName(user2.getLastName());
			}

			if (user.getAttendaces() == null) {
				user.setAttendaces(user2.getAttendaces());
			}
			if (user.getEmail() == null) {
				user.setEmail(user2.getEmail());
			}
			if (user.getDepartment() == null) {
				user.setDepartment(user2.getDepartment());
			}
			if (user.getMobile() == null) {
				user.setMobile(user2.getMobile());
			}
			if (user.getPassword() == null) {
				user.setPassword(user2.getPassword());
			}
			if (user.getPosition() == null) {
				user.setPosition(user2.getPosition());
			}
			if (user.getRole() == null) {
				user.setRole(user2.getRole());
			}
			if (user.getSalary() == null) {
				user.setSalary(user2.getSalary());
			}
			if (user.getAddresses().isEmpty()) {
				user.setAddresses(user2.getAddresses());
			}
			if (user.getTasks().isEmpty()) {
				user.setTasks(user2.getTasks());
			}

			User user3 = repo.save(user);
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(user3);
			structure.setMessage("Employee Updated");
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		} else {
			throw new NoUserPresentException("No Employee Present With the given Id : " + empId);
		}

	}

	public ResponseEntity<ResponseStructure<List<User>>> viewEmployees() {
		if (repo.findAll().isEmpty()) {
			throw new ResourceNotFoundException("No Employees Present to show");
		}
		ResponseStructure<List<User>> structure = new ResponseStructure<List<User>>();

		structure.setData(repo.findAll());
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Employees Found");
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<User>> viewEmpById(Long id) {
		if (repo.findById(id).isPresent()) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Employee Found");
			structure.setData(repo.findById(id).get());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		} else {
			throw new NoUserPresentException("Employee not found with the given id: " + id);
		}
	}

	public ResponseEntity<ResponseStructure<User>> assignHrRole(Long empId) {
		Optional<User> optional = repo.findById(empId);
		if (optional.isPresent()) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			User user = optional.get();

			UserRole role = user.getRole();
			switch (role) {
			case ROLE_EMPLOYEE:
				user.setRole(UserRole.ROLE_HR);
				User user2 = repo.save(user);
				structure.setMessage("HR role assigned to " + user2.getFirstName());
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(user2);
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			case ROLE_HR:
				structure.setMessage("The User already has HR Role");
				structure.setStatus(HttpStatus.NO_CONTENT.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NO_CONTENT);
			}

		}

		throw new NoUserPresentException("User Not Found With the given id: " + empId);

	}

	public ResponseEntity<ResponseStructure<List<Task>>> viewEmpTask(Long empId) {
		User user = repo.findById(empId).get();
		if (user != null) {
			List<Task> task = taskRepo.findByUserId(empId);
			ResponseStructure<List<Task>> structure = new ResponseStructure<List<Task>>();
			if (task == null) {
				throw new ResourceNotFoundException("No task Assigned To" + user.getFirstName());
			} else {
				structure.setData(task);
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage(user.getFirstName() + " is working on this task");
			}
			return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.OK);

		} else {
			throw new NoUserPresentException("No Employee present with the given id : " + empId);
		}
	}

}

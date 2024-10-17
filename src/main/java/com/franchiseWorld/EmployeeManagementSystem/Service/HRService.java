package com.franchiseWorld.EmployeeManagementSystem.Service;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Attendance;
import com.franchiseWorld.EmployeeManagementSystem.Entity.User;
import com.franchiseWorld.EmployeeManagementSystem.Repository.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HRService {

    @Autowired
    private HRRepository hrRepository;

    public User findUserByIdOrName(String name,Long id) {
        return  hrRepository.findByIdOrFirstName(id,name);
    }

    public List<User> viewUserList() {
        return hrRepository.findAll();
    }

    public void updateAttendance(Long userId, Attendance attendance) {
        User user = hrRepository.findById(userId).orElseThrow(() -> new RuntimeException("Employee not found"));
        user.setAttendaces((List<Attendance>) attendance);
        hrRepository.save(user);
    }

    public void updateTask(Long userId, Task task) {
        User user = hrRepository.findById(userId).orElseThrow(() -> new RuntimeException("Employee not found"));
        user.getTasks().add(task);
        hrRepository.save(user);
    }

    public void updateSalary(Long userId, Long salary) {
        User user = hrRepository.findById(userId).orElseThrow(() -> new RuntimeException("Employee not found"));
        user.setSalary(salary);
        hrRepository.save(user);
    }

    public List<Task> seeEmpTaskProgress(Long employeeId) {
        User user = hrRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return user.getTasks();
    }


}

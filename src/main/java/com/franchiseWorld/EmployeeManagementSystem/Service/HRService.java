package com.franchiseWorld.EmployeeManagementSystem.Service;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Attendance;
import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;
import com.franchiseWorld.EmployeeManagementSystem.Entity.User;
import com.franchiseWorld.EmployeeManagementSystem.Repository.AttendanceRepository;
import com.franchiseWorld.EmployeeManagementSystem.Repository.HRRepository;
import com.franchiseWorld.EmployeeManagementSystem.Repository.TaskRepository;
import com.franchiseWorld.EmployeeManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HRService {

    @Autowired
    private HRRepository hrRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<User> getAllUsers() {
        return hrRepository.findAll();
    }

    public User getUserById(Long id) {
        return hrRepository.findById(id).orElse(null);
    }



    public void updateAttendance(Long userId, Attendance attendance) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            attendance.setAttendace(attendance.isAttendace());
            attendanceRepository.save(attendance);
        }
    }



    public void updateTask(Long userId, Task task) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            task.setUser(user.get());
            taskRepository.save(task);
        }

    }



        public void updateSalary(Long userId, Long newSalary) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                User user1 = user.get();
                user1.setSalary(newSalary);
                userRepository.save(user1);
            }
        }




        //
        public Task seeEmpTaskProgress(Long taskId) {
            return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        }


    }
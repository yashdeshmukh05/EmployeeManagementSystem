package com.franchiseWorld.EmployeeManagementSystem.controller;

import com.franchiseWorld.EmployeeManagementSystem.Entity.Attendance;
import com.franchiseWorld.EmployeeManagementSystem.Entity.Task;
import com.franchiseWorld.EmployeeManagementSystem.Entity.User;
import com.franchiseWorld.EmployeeManagementSystem.Service.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr")
public class HRController {


    @Autowired
    private HRService hrService;

    @GetMapping("/users")
    public List<User> viewUserList() {
        return hrService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id) {
        return hrService.getUserById(id);
    }

    @PutMapping("/user/{id}/attendance")
    public void updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        hrService.updateAttendance(id, attendance);
    }

    @PutMapping("/user/{id}/task")
    public void updateTaskProgress(@PathVariable Long id, @RequestBody Task task) {
        hrService.updateTask(id, task);
    }

    @PutMapping("/{id}/salary")
    public ResponseEntity<Void> updateSalary(@PathVariable Long id, @RequestBody Long newSalary) {
        hrService.updateSalary(id, newSalary);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/task/{id}/progress")
    public ResponseEntity<com.franchiseWorld.EmployeeManagementSystem.Entity.Task> seeEmpTaskProgress(@PathVariable Long id) {
        return ResponseEntity.ok(hrService.seeEmpTaskProgress(id));
    }
}

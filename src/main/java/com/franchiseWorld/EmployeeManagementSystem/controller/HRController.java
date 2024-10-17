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

    //find employee id or name API
    @GetMapping("/user/{identifier}")
    public ResponseEntity<User> findUserByIdOrName(@PathVariable String name,Long id) {
        User employee = hrService.findUserByIdOrName(name, id);
        return ResponseEntity.ok(employee);
    }
    //View All Employee API
    @GetMapping("/users")
    public ResponseEntity<List<User>> viewUserList() {
        List<User> employees = hrService.viewUserList();
        return ResponseEntity.ok(employees);
    }

    //Update Attendance API
    @PutMapping("/attendance/{employeeId}")
    public ResponseEntity<Void> updateAttendance(@PathVariable Long employeeId, @RequestBody Attendance attendance) {
        hrService.updateAttendance(employeeId, attendance);
        return ResponseEntity.noContent().build();
    }

    //Update Task API
    @PostMapping("/task/{employeeId}")
    public ResponseEntity<Void> updateTask(@PathVariable Long employeeId, @RequestBody Task task) {
        hrService.updateTask(employeeId, task);
        return ResponseEntity.noContent().build();
    }

    //Update Salary API
    @PutMapping("/salary/{employeeId}")
    public ResponseEntity<Void> updateSalary(@PathVariable Long employeeId, @RequestBody Long salary) {
        hrService.updateSalary(employeeId, salary);
        return ResponseEntity.noContent().build();
    }

    //See Employee Task is Progress
    @GetMapping("/taskProgress/{employeeId}")
    public ResponseEntity<List<Task>> seeEmpTaskProgress(@PathVariable Long employeeId) {
        List<Task> taskProgressList = hrService.seeEmpTaskProgress(employeeId);
        return ResponseEntity.ok(taskProgressList);
    }
}

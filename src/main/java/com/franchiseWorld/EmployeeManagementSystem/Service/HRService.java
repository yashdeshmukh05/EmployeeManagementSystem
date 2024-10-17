package com.franchiseWorld.EmployeeManagementSystem.Service;

import com.franchiseWorld.EmployeeManagementSystem.Repository.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRService {

    @Autowired
    private HRRepository hrRepository;
}

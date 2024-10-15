package com.franchiseWorld.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "password")
        private String password;

        @Column(name = "email")
        private String email;

        private UserRole role;

        private String mobile;

        private Long salary;

        @OneToOne
        private Position position;

        @OneToOne
        private Department department;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Address> addresses = new ArrayList<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Attendance> attendaces = new ArrayList<>();
    }

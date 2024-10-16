package com.franchiseWorld.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
    @GeneratedValue(strategy = GenerationType.AUTO)
>>>>>>> EmployeeControllerNew
    private int id;

    @Column(name= "position_name")
    private String position_name;
}

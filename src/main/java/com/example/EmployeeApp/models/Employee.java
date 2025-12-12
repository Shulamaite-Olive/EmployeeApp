package com.example.EmployeeApp.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String role;
}

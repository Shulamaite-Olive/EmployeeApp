package com.example.EmployeeApp.controllers;

import com.example.EmployeeApp.models.Employee;
import com.example.EmployeeApp.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeApi {

    private final EmployeeService employeeService;

    public EmployeeApi(EmployeeService service) {
        this.employeeService = service;
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee created = employeeService.create(employee);
        return ResponseEntity.created(URI.create("/employees/" + created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(employeeService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Employee employee = employeeService.get(id);
        if (employee != null) return ResponseEntity.ok(employee);
        return ResponseEntity.status(404).body("Employee with ID " + id + " not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee incoming) {
        Employee updated = employeeService.update(id, incoming);
        if (updated != null) return ResponseEntity.ok(updated);
        return ResponseEntity.status(404).body("Cannot update. Employee with ID " + id + " not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean removed = employeeService.delete(id);
        if (removed) return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
        return ResponseEntity.status(404).body("Cannot delete. Employee with ID " + id + " not found");
    }
}

package com.example.EmployeeApp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeApi {

    private final List<Employee> employees = new ArrayList<>();

    // CREATE
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employees.add(employee);
        return ResponseEntity.created(URI.create("/employees/" + employee.getId()))
                .body(employee);
    }

    // READ ALL
    @GetMapping
    public List<Employee> list() {
        return employees;
    }

    // READ ONE — MUST RETURN ResponseEntity<?>
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {

        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (employee != null) {
            return ResponseEntity.ok(employee); // return Employee
        } else {
            return ResponseEntity.status(404)
                    .body("Employee with id " + id + " not found"); // return String
        }
    }


    // UPDATE — MUST RETURN ResponseEntity<?>
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee incoming) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                e.setName(incoming.getName());
                e.setRole(incoming.getRole());
                return ResponseEntity.ok(e);
            }
        }
        return ResponseEntity.status(404).body("Cannot update. Employee with id " + id + " not found");
    }

    // DELETE — MUST RETURN ResponseEntity<?>
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean removed = employees.removeIf(e -> e.getId().equals(id));

        if (removed) {
            return ResponseEntity.ok("Employee with id " + id + " deleted successfully");
        }

        return ResponseEntity.status(404)
                .body("Cannot delete. Employee with id " + id + " not found");
    }
}

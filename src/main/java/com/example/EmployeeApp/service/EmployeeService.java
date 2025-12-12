package com.example.EmployeeApp.service;

import com.example.EmployeeApp.models.Employee;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    public Employee create(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public List<Employee> list() {
        return employees;
    }

    public Employee get(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Employee update(Long id, Employee incoming) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                e.setName(incoming.getName());
                e.setRole(incoming.getRole());
                return e;
            }
        }
        return null;
    }

    public boolean delete(Long id) {
        return employees.removeIf(e -> e.getId().equals(id));
    }
}

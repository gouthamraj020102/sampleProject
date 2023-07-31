package com.sampleProject.springboot.Controllers;

import com.sampleProject.springboot.Entities.Employee;
import com.sampleProject.springboot.Exception.EmployeeNotFoundException;
import com.sampleProject.springboot.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.employeeService.createEmployee(employee));
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            return ResponseEntity.ok(this.employeeService.getEmployees());
        }
        catch (EmployeeNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(this.employeeService.getEmployeeDetails(id));
        }
        catch (EmployeeNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

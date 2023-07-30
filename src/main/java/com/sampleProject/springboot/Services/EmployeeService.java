package com.sampleProject.springboot.Services;

import com.sampleProject.springboot.Entities.Employee;
import com.sampleProject.springboot.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        if (employee != null) {
            Employee employee1 = this.employeeRepository.save(employee);
            return employee1;
        } else {
            throw new IllegalArgumentException("Employee details should not be null");
        }
    }
}
